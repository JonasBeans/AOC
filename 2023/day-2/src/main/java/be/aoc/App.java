package be.aoc;

import be.aoc.service.CubeService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CubeService service = new CubeService();
        service.readFile(args[0]);
    }
}
