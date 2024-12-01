package be.aoc.model;

public class Cube {
    private CubeColor color;
    private Integer amount;

    public enum CubeColor{
        RED, BLUE, GREEN, UNKNOWN;

        public static CubeColor getColor(String color){
            switch (color.toUpperCase()){
                case "RED":
                    return RED;
                case "GREEN":
                    return GREEN;
                case "BLUE" :
                    return BLUE;
                default:
                    return UNKNOWN;
            }
        }
    }

    public Cube(CubeColor color, Integer amount) {
        this.color = color;
        this.amount = amount;
    }

    public static Cube convertLineTo(String line, String delimiter){
        String[] result = line.split(delimiter);
        Integer amount = Integer.parseInt(result[0]);
        CubeColor color = CubeColor.getColor(result[1]);
        return new Cube(color, amount);
    }
}
