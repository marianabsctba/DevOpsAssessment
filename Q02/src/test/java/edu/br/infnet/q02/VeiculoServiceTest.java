package edu.br.infnet.q02;

import edu.br.infnet.q02.model.Veiculo;
import edu.br.infnet.q02.repository.VeiculoRepository;
import edu.br.infnet.q02.service.VeiculoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;

    @MockBean
    private VeiculoRepository veiculoRepository;

    @Test
    public void listarVeiculos_DeveRetornarListaDeVeiculos() {
        List<Veiculo> veiculos = List.of(
                new Veiculo(1L, "XYZ7890", "Toyota", "Corolla", 2020),
                new Veiculo(2L, "ABC1234", "Honda", "Civic", 2021)
        );

        when(veiculoRepository.findAll()).thenReturn(veiculos);

        List<Veiculo> result = veiculoService.listarVeiculos();
        assertEquals(2, result.size());
        assertEquals("Toyota", result.get(0).getMarca());
        assertEquals("Honda", result.get(1).getMarca());
    }

    @Test
    public void buscarVeiculoPorId_DeveRetornarVeiculo_QuandoIdExiste() {
        Veiculo veiculo = new Veiculo(1L, "XYZ7890", "Toyota", "Corolla", 2020);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Optional<Veiculo> result = veiculoService.buscarVeiculoPorId(1L);
        assertTrue(result.isPresent());
        assertEquals("Toyota", result.get().getMarca());
    }

    @Test
    public void buscarVeiculoPorId_DeveRetornarOptionalVazio_QuandoIdNaoExiste() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Veiculo> result = veiculoService.buscarVeiculoPorId(1L);
        assertFalse(result.isPresent());
    }

    @Test
    public void salvarVeiculo_DeveSalvarVeiculo() {
        Veiculo veiculo = new Veiculo(1L, "XYZ7890", "Toyota", "Corolla", 2020);

        when(veiculoRepository.save(veiculo)).thenReturn(veiculo);

        Veiculo result = veiculoService.salvarVeiculo(veiculo);
        assertNotNull(result);
        assertEquals("Toyota", result.getMarca());
    }

    @Test
    public void atualizarVeiculo_DeveAtualizarVeiculo() {
        Veiculo veiculo = new Veiculo(1L, "XYZ7890", "Toyota", "Corolla", 2020);
        Veiculo veiculoAtualizado = new Veiculo(1L, "XYZ7890", "Toyota", "Corolla", 2021);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(veiculoAtualizado)).thenReturn(veiculoAtualizado);

        Veiculo result = veiculoService.atualizarVeiculo(1L, veiculoAtualizado);
        assertNotNull(result);
        assertEquals(2021, result.getAno());
    }

    @Test
    public void atualizarVeiculo_DeveLancarExcecao_QuandoVeiculoNaoExiste() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Veiculo veiculoAtualizado = new Veiculo(1L, "XYZ7890", "Toyota", "Corolla", 2021);
        assertThrows(RuntimeException.class, () -> veiculoService.atualizarVeiculo(1L, veiculoAtualizado));
    }

    @Test
    public void excluirVeiculo_DeveExcluirVeiculo() {
        Veiculo veiculo = new Veiculo(1L, "XYZ7890", "Toyota", "Corolla", 2020);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        veiculoService.excluirVeiculo(1L);
        verify(veiculoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void excluirVeiculo_DeveLancarExcecao_QuandoVeiculoNaoExiste() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> veiculoService.excluirVeiculo(1L));
    }
}
