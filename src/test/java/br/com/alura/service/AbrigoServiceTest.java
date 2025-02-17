package br.com.alura.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;

public class AbrigoServiceTest {

  private ClientHttpConfiguration client = Mockito.mock(ClientHttpConfiguration.class);
  private AbrigoService abrigoService = new AbrigoService(client);
  private HttpResponse<String> response = Mockito.mock(HttpResponse.class);
  private Abrigo abrigo = new Abrigo("Teste", "1112345678", "teste@teste.com");

  @Test
  public void deveVerificarQuandoHaAbrigo() throws IOException, InterruptedException{
    abrigo.setId(0L);
    String expectedAbrigosCadastrados = "Abrigos cadastrados:";
    String expectedIdENome = "0 - Teste";

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);

    Mockito.when(response.body()).thenReturn("[{"+abrigo.toString()+"}]");
    Mockito.when(client.requisicaoGet(Mockito.anyString())).thenReturn(response);

    abrigoService.listarAbrigos();

    String[] lines = baos.toString().split(System.lineSeparator());
    System.out.println(lines);
    String actualAbrigosCadastrados = lines[0];
    String actualIdENome = lines[1];

    Assert.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
    Assert.assertEquals(expectedIdENome, actualIdENome);

  }

  @Test
  public void deveVerificarQuandoNaoHaAbrigo() throws IOException, InterruptedException{
    String expected = "Não há abrigos cadastrados";

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);

    Mockito.when(response.body()).thenReturn("[]");
    Mockito.when(client.requisicaoGet(Mockito.anyString())).thenReturn(response);

    abrigoService.listarAbrigos();

    String[] lines = baos.toString().split(System.lineSeparator());
    String actual = lines[0];

    Assert.assertEquals(expected, actual);
  }
}
