package br.com.alura.roger.series.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> clazz);
}
