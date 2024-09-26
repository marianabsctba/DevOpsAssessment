package edu.br.infnet.q02;

import edu.br.infnet.q02.model.Veiculo;
import edu.br.infnet.q02.service.VeiculoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VeiculoService veiculoService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void listarVeiculos_DeveRetornarListaDeVeiculos() throws Exception {
        List<Veiculo> veiculos = List.of(
                new Veiculo(1L, "ABC1234", "Toyota", "Corolla", 2020),
                new Veiculo(2L, "DEF5678", "Honda", "Civic", 2021)
        );

        when(veiculoService.listarVeiculos()).thenReturn(veiculos);

        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].placa").value("ABC1234"))
                .andExpect(jsonPath("$[0].marca").value("Toyota"))
                .andExpect(jsonPath("$[1].placa").value("DEF5678"))
                .andExpect(jsonPath("$[1].marca").value("Honda"));
    }

    @Test
    public void buscarVeiculoPorId_DeveRetornarVeiculo_QuandoIdExiste() throws Exception {
        Veiculo veiculo = new Veiculo(1L, "ABC1234", "Toyota", "Corolla", 2020);

        when(veiculoService.buscarVeiculoPorId(1L)).thenReturn(Optional.of(veiculo));

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.placa").value("ABC1234"))
                .andExpect(jsonPath("$.marca").value("Toyota"));
    }

    @Test
    public void buscarVeiculoPorId_DeveRetornar404_QuandoIdNaoExiste() throws Exception {
        when(veiculoService.buscarVeiculoPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/veiculos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void salvarVeiculo_DeveSalvarVeiculo() throws Exception {
        Veiculo veiculo = new Veiculo(1L, "ABC1234", "Toyota", "Corolla", 2020);

        when(veiculoService.salvarVeiculo(any(Veiculo.class))).thenReturn(veiculo);

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(veiculo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.placa").value("ABC1234"))
                .andExpect(jsonPath("$.marca").value("Toyota"));
    }

    @Test
    public void atualizarVeiculo_DeveAtualizarVeiculo() throws Exception {
        Veiculo veiculoAtualizado = new Veiculo(1L, "ABC1234", "Toyota", "Corolla", 2021);

        when(veiculoService.atualizarVeiculo(eq(1L), any(Veiculo.class))).thenReturn(veiculoAtualizado);

        mockMvc.perform(put("/veiculos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(veiculoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ano").value(2021));
    }

    @Test
    public void excluirVeiculo_DeveExcluirVeiculo() throws Exception {
        mockMvc.perform(delete("/veiculos/1"))
                .andExpect(status().isNoContent());

        verify(veiculoService, times(1)).excluirVeiculo(1L);
    }
}
