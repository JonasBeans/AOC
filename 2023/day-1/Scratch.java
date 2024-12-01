class Scratch{
    public static void main(String[] args) {
        String string = "five3mzqgxnbmdbcmntfiveeightsixtc";
        for (NUMBERS number : NUMBERS.values()) {
             string = string.replaceAll(number.toString(), number.getValue());
        }
        System.out.println(string);
    }

    public enum NUMBERS{
        one("1"), 
        five("5");

        String value;

        NUMBERS(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }
    }
}
