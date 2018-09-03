package src.test.java.com.generation;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import src.main.java.com.generation.SimplexNoise;

public class SimplexNoiseTest {

	@Test
	public void testGenerateHeightMap() {
		
		final int WIDTH = 256;
		final int HEIGHT = 256;
		final int X = 0;
		final int Y = 0;
		final String RESOURCE_NAME = "src/test/java/com/generation/expected-result.png";
		
		float[][] heightmap = new SimplexNoise(50, 0.3F, 1111111111111111L).generateHeightMap(X, Y, WIDTH, HEIGHT);
		BufferedImage image = null;
		
		try(InputStream in = this.getClass().getClassLoader().getResourceAsStream(RESOURCE_NAME)) {
			
			image = ImageIO.read(in);
			
			assertEquals(WIDTH, image.getWidth());
			assertEquals(HEIGHT, image.getHeight());
			
		} catch(IOException | IllegalArgumentException exception) {
			
			fail(exception);
		} 
		
		for(int x = 0; x < WIDTH; x++) {
			
			for(int y = 0; y < HEIGHT; y++) {
				
				assertEquals(new Color(image.getRGB(x, y)).getRed(), (int)(heightmap[x][y] * 255));
			}
		}
	}
}
