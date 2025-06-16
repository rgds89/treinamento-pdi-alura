package br.com.alura.pixstream;

import br.com.alura.pixstream.dto.PixDTO;
import br.com.alura.pixstream.serdes.PixSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PixConsumer {
    private static BigDecimal AMOUNT_VALIDATE = new BigDecimal(10000);
    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder) {

        KStream<String, PixDTO> messageStream = streamsBuilder
                .stream("pix-topic", Consumed.with(Serdes.String(), PixSerdes.serdes()))
                .peek((key, value) -> System.out.println("Pix recebido: " + value.sourceKey()))
                .filter((key, value) -> AMOUNT_VALIDATE.compareTo(value.amount()) == 1 )
                .peek((key, value) -> System.out.println("Pix: " + key + " será verificado para possível frause"));

        messageStream.print(Printed.toSysOut());
        messageStream.to("pix-verificacao-fraude", Produced.with(Serdes.String(), PixSerdes.serdes()));

        KTable<String, Double> aggregateStream = streamsBuilder
                .stream("pix-topic-2", Consumed.with(Serdes.String(), PixSerdes.serdes()))
                .peek((key, value) -> System.out.println("Pix recebido: " + value.sourceKey()))
                .filter((key, value) -> value.amount() != null)
                .groupBy((key, value) -> value.sourceKey())
                .aggregate(
                        () -> 0.0,
                        (key, value, aggregate) -> (aggregate + value.amount().doubleValue()),
                        Materialized.with(Serdes.String(), Serdes.Double())
                );


        aggregateStream.toStream().print(Printed.toSysOut());



    }
}
