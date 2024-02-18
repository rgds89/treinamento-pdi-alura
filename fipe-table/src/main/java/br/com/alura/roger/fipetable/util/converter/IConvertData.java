package br.com.alura.roger.fipetable.util.converter;

import java.util.List;

public interface IConvertData {
    <T> T convertData(String data, Class<T> type);

    <T> List<T> convertDataList(String data, Class<T> type);
}
