package domain.documents;

import java.util.random.RandomGenerator;

public class Documento {
    private Long identificador;
    private Integer idCriador;
    private Integer idResponsavel;
    private String link;
    private Boolean arquivado;

    public Documento(Integer idCriador, Integer idResponsavel, String link) {
        this.identificador = RandomGenerator.getDefault().nextLong();
        this.idCriador = idCriador;
        this.idResponsavel = idResponsavel;
        this.link = link;
        this.arquivado = false;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "identificador=" + identificador +
                ", idCriador=" + idCriador +
                ", idResponsavel=" + idResponsavel +
                ", link='" + link + '\'' +
                ", arquivado=" + arquivado +
                '}';
    }

    public Long getIdentificador() {
        return identificador;
    }

    public Integer getIdCriador() {
        return idCriador;
    }

    public Integer getIdResponsavel() {
        return idResponsavel;
    }

    public String getLink() {
        return link;
    }

    public Boolean getArquivado() {
        return arquivado;
    }

}
