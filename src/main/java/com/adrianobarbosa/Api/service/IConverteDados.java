package com.adrianobarbosa.Api.service;

public interface IConverteDados {
   <T> T obterDados(String json, Class<T> classe);
}
