package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PetServiceTest {

  private ClientHttpConfiguration client = Mockito.mock(ClientHttpConfiguration.class);
  private PetService petService = new PetService(client);
  private HttpResponse response = Mockito.mock(HttpResponse.class);
  private Pet pet = new Pet("CACHORRO", "Sophie", "SRD", 8, "preta", 14);

  @Test
  public void deveVerificarQuandoHaPets() throws IOException, InterruptedException {
    pet.setId(0L);
    String expectedCabecalhoPets = "Pets cadastrados:";
    String expectedPetsCadastrados = "0 - CACHORRO - Sophie - SRD - 8 ano(s)";

    String userInput = String.format(" ", System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);

    Mockito.when(response.body()).thenReturn("[{" + pet.toString() + "}]");
    Mockito.when(client.requisicaoGet(Mockito.anyString())).thenReturn(response);

    petService.listarPetsAbrigo();

    String[] lines = baos.toString().split(System.lineSeparator());
    String actualCabecalhoPets = lines[1];
    String actualPetsCadastrados = lines[2];

    Assert.assertEquals(expectedCabecalhoPets, actualCabecalhoPets);
    Assert.assertEquals(expectedPetsCadastrados, actualPetsCadastrados);
  }
}
