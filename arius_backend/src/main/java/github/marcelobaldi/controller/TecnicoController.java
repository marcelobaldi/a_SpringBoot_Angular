//Pacote, Libs, Arquivos
package github.marcelobaldi.controller;
import github.marcelobaldi.entity.Tecnicos;
import github.marcelobaldi.exception.RegraNegocioException;
import github.marcelobaldi.service.TecnicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Classe (Anotações API) - O CrossOrigin é para o Angular!!!
@RestController
@RequestMapping("/api/tecnicos")
@CrossOrigin("http://localhost:4200")
public class TecnicoController {

    //Variável e Construtor (Acesso à Classe Service)
    private TecnicoService tecnicoService;
    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    //Método Salvar - Verbo API Post
    @PostMapping
    public ResponseEntity salvar(@RequestBody Tecnicos tecnicos){
        try{
            tecnicoService.salvar(tecnicos);
            return new ResponseEntity(tecnicos, HttpStatus.CREATED);        //Status 201
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());        //Status 404
        }
    }

    //Método Atualizar - Verbo API PUT
    @PutMapping("{id}")
    public ResponseEntity atualizar ( @PathVariable("id") Integer id,
                                      @RequestBody Tecnicos tecnicos){
        return tecnicoService.buscarPorId(id).map(entity -> {
            try{
               tecnicos.setId(entity.getId());
               tecnicoService.atualizar(tecnicos);
               return ResponseEntity.ok(tecnicos);
            }catch (RegraNegocioException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet( () ->
                new ResponseEntity("Técnico Não Encontrado", HttpStatus.BAD_REQUEST));
    }

    //Método Deletar - Verbo API Delete
    @DeleteMapping("{id}")
    public ResponseEntity deletar (@PathVariable("id") Integer id){
        return tecnicoService.buscarPorId(id).map(tecnico -> {
            tecnicoService.deletar(tecnico);
            return new ResponseEntity (HttpStatus.NO_CONTENT);
        }).orElseGet( () ->
                new ResponseEntity("Técnico Não Encontrado", HttpStatus.BAD_REQUEST));
    }

    //Método Buscar (Todos) - Verbo API GET
    @GetMapping
    public ResponseEntity buscarTodos(
            @RequestParam(value = "nome", required  = false) String nome,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "especialidade", required = false) String especialidade,
            @RequestParam(value = "descricao", required = false) String descricao
            ){

        Tecnicos tecnicosFiltro = new Tecnicos();
        tecnicosFiltro.setNome(nome);
        tecnicosFiltro.setEmail(email);
        tecnicosFiltro.setEspecialidade(especialidade);
        tecnicosFiltro.setDescricao(descricao);

        List<Tecnicos> tecnicos = tecnicoService.buscar(tecnicosFiltro);
        if(tecnicos.size() < 1){
            return ResponseEntity.badRequest().body("Não Há Técnicos Cadastrados");
        }else{
            return ResponseEntity.ok(tecnicos);
        }
    }

    //Método Buscar (Por Id) - Verbo API GET
    @GetMapping("{id}")
    public ResponseEntity buscarPorId (@PathVariable("id") Integer id){
        return tecnicoService.buscarPorId(id).map(tecnico -> {
            tecnicoService.buscarPorId(id);
            return ResponseEntity.ok(tecnico);
        }).orElseGet( () ->
                new ResponseEntity("Técnico Não Encontrado", HttpStatus.BAD_REQUEST));
    }
}

