import com.goup.entities.Estoque;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.usuarios.CargoRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.repositories.wip.EstoqueRepository;
import com.goup.utils.CsvEstoque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/csv")
public class ProdutoController {


    @Autowired
    private EstoqueRepository estoqueRepository;

    @GetMapping("/csv/produtos/loja/{lojaId}")
    public ResponseEntity<Void> gerarCsvProdutosPorLoja(@PathVariable int lojaId) {
        List<Estoque> estoques = estoqueRepository.findAllByLojaId(lojaId);
        if (!estoques.isEmpty()) {
            CsvEstoque csvEstoque = new CsvEstoque();
            csvEstoque.writeStockToCSV(estoques);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @GetMapping("/csv/produtos")
    public ResponseEntity<Void> gerarCsvTodosProdutos() {
        List<Estoque> estoques = estoqueRepository.findAll();
        if (!estoques.isEmpty()) {
            CsvEstoque csvProdutos = new CsvEstoque();
            csvProdutos.writeStockToCSV(estoques);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(204).build();
        }
    }
}\