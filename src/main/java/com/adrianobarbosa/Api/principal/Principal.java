package com.adrianobarbosa.Api.principal;

import com.adrianobarbosa.Api.model.DadosCep;
import com.adrianobarbosa.Api.repository.CepRepository;
import com.adrianobarbosa.Api.service.ConsumoCep;
import com.adrianobarbosa.Api.service.ConverterDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoCep consumo = new ConsumoCep();
    private ConverterDados coversor = new ConverterDados();
    private final String ENDERECO = "https://brasilapi.com.br/api/cep/v1/";
    private final CepRepository repository;

    @Autowired
    public Principal(CepRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu =
                    "1 - Buscar CEP\n"+
                    "2 - Listar CEP\n"+
                    "0 - Sair\n"+
                    "Escolha uma opção: ";

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){
                case 1:
                    buscarCep();
                    break;
                case 2:
                    exibirCep();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

    }

    private void buscarCep(){
        System.out.println("Digite o CEP: ");
        var CepBusca = leitura.nextLine();
        var json = consumo.obterCep(ENDERECO + CepBusca.replace("-", "+"));
        DadosCep dadosCep = coversor.obterDados(json, DadosCep.class);

        try {
            if (dadosCep != null) {
                salvarDadosCep(dadosCep);
                System.out.println("Dados do CEP salvos com sucesso: " + dadosCep);
            } else {
                System.out.println("Não foi possivel obter os dados do CEP.");
            }
        }catch (DataIntegrityViolationException e){
            System.out.println("O CEP já existe no banco de dados.");
        }catch (Exception e){
            System.out.println("Ocorreu um erro ao salvar o CEP: " + e.getMessage());
        }
    }

    private void salvarDadosCep(DadosCep dadosCep){
        repository.save(dadosCep);
    }

    private void exibirCep(){
        List<DadosCep> listaCeps = repository.findAll();
        if (!listaCeps.isEmpty()) {
            System.out.println("CEPs salvos no banco de dados:");
            for (DadosCep cep : listaCeps) {
                System.out.println(cep);
            }
        } else {
            System.out.println("Nenhum CEP encontrado no banco de dados.");
        }
    }
}