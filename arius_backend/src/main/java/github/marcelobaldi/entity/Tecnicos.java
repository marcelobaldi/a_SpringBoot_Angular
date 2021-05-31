//Pacote, Libs, Arquivos
package github.marcelobaldi.entity;
import javax.persistence.*;

//Classe (Anotações Entidade)
@Entity                                                     //Banco Dados       (Tabela)
@Table(name="tecnicos", schema = "arius")                   //Nome              (Tabela e BD)
public class Tecnicos {                                     //Classe            (Acesso e Nome)

    //Campo - ID
    @Id                                                     //Obrigatório       (Quando Entidade)
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //Preenchimento     (Auto Incremento)
    @Column(name = "id")                                    //Nome              (Campo na Tabela)
    private Integer id;                                     //Variável          (Acesso, Tipo, Nome)

    //Campo - Nome
    @Column(name = "nome", length = 100)
    private String  nome;

    //Campo - Email
    @Column(name = "email", length = 100)
    private String  email;

    //Campo - Especialidade
    @Column(name = "especialidade", length = 100)
    private String  especialidade;

    //Campo - Descrição
    @Column(name = "descricao", length = 250)
    private String  descricao;

    //Construtores                                          //Acessar Classe    (Vazio, SemId, ComId)
    public Tecnicos() { }
    public Tecnicos(String nome, String email, String especialidade, String descricao) { this.nome = nome;this.email = email;this.especialidade = especialidade;this.descricao = descricao; }
    public Tecnicos(Integer id, String nome, String email, String especialidade, String descricao) { this.id = id;this.nome = nome;this.email = email;this.especialidade = especialidade;this.descricao = descricao; }

    //GetterSetter                                          //Acessar Atributos (Quando Private)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    //Conversão - String                                    //Facilitar Debug   (Lista - ForEach)
    @Override
    public String toString() {
        return "Tecnicos{" + "id=" + id + ", nome='" + nome + '\'' + ", email='" + email + '\'' + ", especialidade='" + especialidade + '\'' + ", descricao='" + descricao + '\'' + '}';
    }
}
