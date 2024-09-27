package br.com.fiap.Simpsons.controller;

import br.com.fiap.Simpsons.model.Personagem;
import br.com.fiap.Simpsons.service.PersonagemService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PersonagemController {
    private PersonagemService personagemService;

    public PersonagemController(PersonagemService personagemService) {
        this.personagemService = personagemService;
    }

    public void listarPersonagens() {
        try {
            List<Personagem> personagens = personagemService.listar();
            for (Personagem p : personagens) {
                System.out.println(p.getId() + ": " + p.getName());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar personagens: " + e.getMessage());
        }
    }

    public void cadastrarPersonagem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do personagem: ");
        String id = scanner.nextLine();
        System.out.print("Digite o nome do personagem: ");
        String name = scanner.nextLine();
        System.out.print("Digite o nome normalizado do personagem: ");
        String normalizedName = scanner.nextLine();
        System.out.print("Digite o gênero do personagem: ");
        String gender = scanner.nextLine();

        Personagem personagem = new Personagem(id, name, normalizedName, gender);
        try {
            personagemService.cadastrar(personagem);
            System.out.println("Personagem cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar personagem: " + e.getMessage());
        }
    }

    public void buscarPorPK() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do personagem para buscar: ");
        String id = scanner.nextLine();
        try {
            Personagem personagem = personagemService.buscarPorPk(id);
            if (personagem != null) {
                System.out.println("Personagem encontrado: " + personagem.getName());
            } else {
                System.out.println("Personagem não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar personagem: " + e.getMessage());
        }
    }

    public void buscarPorCampoEscolhido() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome normalizado do personagem para buscar: ");
        String normalizedName = scanner.nextLine();
        try {
            List<Personagem> personagens = personagemService.buscarPorNomeNormalizado(normalizedName);
            if (!personagens.isEmpty()) {
                for (Personagem p : personagens) {
                    System.out.println("Personagem encontrado: " + p.getName());
                }
            } else {
                System.out.println("Nenhum personagem encontrado com esse nome normalizado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar personagem: " + e.getMessage());
        }
    }

    public void mostrarPersonagem() {
        System.out.println("Mostrando personagem...");
    }
}
