package br.com.fiap.Simpsons.json;

import br.com.fiap.Simpsons.model.Personagem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    private static final String API_URL = "https://api.sampleapis.com/simpsons/characters"; // URL da API dos personagens de "Os Simpsons"
    private static Personagem[] personagens;

    public static void main(String[] args) {
        personagens = buscarPersonagens();
        if (personagens != null) {
            salvarPersonagensEmArquivo("personagens.json");
            pesquisarPersonagemPorId();
        }
    }

    private static Personagem[] buscarPersonagens() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            int codigoResposta = conexao.getResponseCode();

            if (codigoResposta == HttpURLConnection.HTTP_OK) {
                InputStreamReader leitor = new InputStreamReader(conexao.getInputStream());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                return gson.fromJson(leitor, Personagem[].class);
            } else {
                System.out.println("🚫 Algo deu errado ao acessar a API. Código de resposta: " + codigoResposta);
            }
        } catch (IOException e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
        return null;
    }

    private static void salvarPersonagensEmArquivo(String nomeArquivo) {
        try (FileWriter escritor = new FileWriter(nomeArquivo)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(personagens, escritor);
            System.out.println("✅ Os dados dos personagens foram salvos com sucesso em: " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("⚠️ Não foi possível salvar o arquivo: " + e.getMessage());
        }
    }

    private static void pesquisarPersonagemPorId() {
        Scanner scanner = new Scanner(System.in);
        String entrada;

        System.out.print("🔍 Insira o ID do personagem que deseja encontrar: ");
        entrada = scanner.nextLine();

        boolean encontrado = false;

        for (Personagem personagem : personagens) {
            if (personagem.getId().equals(entrada)) {
                exibirDetalhesPersonagem(personagem);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("🙁 Desculpe, não conseguimos encontrar um personagem com esse ID.");
        }

        System.out.println("👋 Deseja encerrar o programa? (s/n)");
        entrada = scanner.nextLine();

        if (entrada.equalsIgnoreCase("s")) {
            System.out.println("👋 Obrigado por usar o sistema! Até a próxima!");
        } else {
            System.out.println("🔄 Reiniciando a pesquisa...");
            pesquisarPersonagemPorId(); // Permite nova pesquisa
        }
    }

    private static void exibirDetalhesPersonagem(Personagem personagem) {
        System.out.println("\n Detalhes do Personagem Encontrado 🎉");
        System.out.println("🆔 ID: " + personagem.getId());
        System.out.println("👤 Nome: " + personagem.getName());
        System.out.println("🔤 Nome Normalizado: " + personagem.getNormalized_name());
        System.out.println("🚻 Gênero: " + personagem.getGender());
        System.out.println("---------------------------------------\n");
    }
}
