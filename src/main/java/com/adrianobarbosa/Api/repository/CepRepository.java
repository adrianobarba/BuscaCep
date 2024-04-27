package com.adrianobarbosa.Api.repository;


import com.adrianobarbosa.Api.model.DadosCep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CepRepository  extends JpaRepository<DadosCep, Long> {

}

