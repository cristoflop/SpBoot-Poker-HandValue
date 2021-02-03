package es.cristoflop.poker.valormano.application.dtos;

public class ManoDto {

    private String cartas;

    public ManoDto() {
    }

    public ManoDto(String cartas) {
        this.cartas = cartas;
    }

    public String getCartas() {
        return this.cartas;
    }

}
