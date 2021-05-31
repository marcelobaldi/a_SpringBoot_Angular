//Pacote, Libs, Arquivos
package github.marcelobaldi.service;
import github.marcelobaldi.entity.Tecnicos;
import github.marcelobaldi.exception.RegraNegocioException;
import github.marcelobaldi.repository.TecnicoDAO;
import github.marcelobaldi.service.serviceimple.TecnicoInterface;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Classe (Anotação Serviço)
@Service
public class TecnicoService implements TecnicoInterface {

    //Variável e Construtor (Acesso à Classe Repository)
    TecnicoDAO tecnicoDAO;
    public TecnicoService(TecnicoDAO tecnicoDAO){
        this.tecnicoDAO = tecnicoDAO;
    }

    //Método - Salvar
    @Override
    @Transactional
    public Tecnicos salvar(Tecnicos tecnicos) {
        validarCampos(tecnicos);

        Tecnicos tecnicoBuscado = tecnicoDAO.buscarPorEmail(tecnicos.getEmail());
        if(tecnicoBuscado == null){
           System.out.println("Tecnico Não Existe");
            return tecnicoDAO.save(tecnicos);
        }
        System.out.println(tecnicoBuscado.getEspecialidade());
        throw new RegraNegocioException("Este Email já Existe");
    }

    //Método - Atualizar
    @Override
    @Transactional
    public Tecnicos atualizar(Tecnicos tecnicos) {
        validarCampos(tecnicos);
        Objects.requireNonNull(tecnicos.getId());                  //Não Aceitar o ID Nulo
        return tecnicoDAO.save(tecnicos);
    }

    //Método - Deletar
    @Override
    @Transactional
    public void deletar(Tecnicos tecnicos) {
        Objects.requireNonNull(tecnicos.getId());                  //Não Aceitar o ID Nulo;
        tecnicoDAO.delete(tecnicos);
    }

    //Método - Buscar Todos (Todos e/ou Contém)
    @Override
    @Transactional(readOnly = true)
    public List<Tecnicos> buscar(Tecnicos tecnicosFiltro) {
        Example example = Example.of(tecnicosFiltro, ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return tecnicoDAO.findAll(example);
    }

    //Método - Buscar Por Id
    @Override
    @Transactional
    public Optional<Tecnicos> buscarPorId(Integer id) {
        return tecnicoDAO.findById(id);
    }

    //Método - Tratamento Erros (Campos)
    @Override
    public void validarCampos(Tecnicos tecnicos) {
        if(tecnicos.getNome() == null || tecnicos.getNome().trim().equals("")
                || tecnicos.getNome().toString().length() < 3){
            throw new RegraNegocioException("Informe um Nome Válido");
        }

        if(tecnicos.getEmail() == null || tecnicos.getEmail().trim().equals("")
                || tecnicos.getEmail().toString().length() < 5){
            throw new RegraNegocioException("Informe um E-Mail Válido");
        }

        if(tecnicos.getEspecialidade() == null || tecnicos.getEspecialidade().trim().equals("")
                || tecnicos.getEspecialidade().toString().length() < 3){
            throw new RegraNegocioException("Informe uma Especialidade Válida");
        }

        if(tecnicos.getDescricao() == null || tecnicos.getDescricao().trim().equals("")
                || tecnicos.getDescricao().toString().length() < 3){
            throw new RegraNegocioException("Informe uma Descrição Válida");
        }
    }
}

