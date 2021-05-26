package Others;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Flood fill, also called seed fill, is an algorithm that determines and alters the area connected
 * to a given node in a multi-dimensional array with some matching attribute. It is used in the
 * "bucket" fill tool of paint programs to fill connected, similarly-colored areas with a different
 * color. (description adapted from https://en.wikipedia.org/wiki/Flood_fill) (see also:
 * https://www.techiedelight.com/flood-fill-algorithm/).
 */
public class FloodFill {
  private static int[][] neighbors = {
    {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
  };

  private static Color black = new Color(0, 0, 0);
  private static Color green = new Color(0, 255, 0);
  private static Color violet = new Color(255, 0, 255);
  private static Color white = new Color(255, 255, 255);
  private static Color orange = new Color(255, 128, 0);

  public static void main(String[] args) {
    testBreadthFirst(new int[] {1, 1}, green, orange, new int[] {1, 1}, orange);
    testBreadthFirst(new int[] {1, 1}, green, orange, new int[] {0, 1}, violet);
    testBreadthFirst(new int[] {1, 1}, green, orange, new int[] {0, 1}, violet);
    testBreadthFirst(new int[] {1, 1}, green, orange, new int[] {6, 4}, white);
    testDepthFirst(new int[] {1, 1}, green, orange, new int[] {1, 1}, orange);
    testDepthFirst(new int[] {1, 1}, green, orange, new int[] {0, 1}, violet);
    testDepthFirst(new int[] {1, 1}, green, orange, new int[] {0, 1}, violet);
    testDepthFirst(new int[] {1, 1}, green, orange, new int[] {6, 4}, white);
  }

  /**
   * Implements the flood fill algorithm through a breadth-first approach using a queue.
   *
   * @param image The image to which the algorithm is applied.
   * @param location The start location on the image.
   * @param targetColor The old color to be replaced.
   * @param replacementColor The new color to replace the old one.
   */
  public static void breadthFirstSearch(
      BufferedImage image, int[] location, Color targetColor, Color replacementColor) {
    if (location[0] < 0
        || location[0] >= image.getWidth()
        || location[1] < 0
        || location[1] >= image.getHeight()) {
      throw new IllegalArgumentException("location should point to a pixel within the image");
    }

    ArrayList<int[]> queue = new ArrayList<int[]>();
    queue.add(location);

    while (queue.size() > 0) {
      breadthFirstFill(image, location, targetColor, replacementColor, queue);
    }
  }

  /**
   * Implements the flood fill algorithm through a depth-first approach using recursion.
   *
   * @param image The image to which the algorithm is applied.
   * @param location The start location on the image.
   * @param targetColor The old color to be replaced.
   * @param replacementColor The new color to replace the old one.
   */
  public static void depthFirstSearch(
      BufferedImage image, int[] location, Color targetColor, Color replacementColor) {
    if (location[0] < 0
        || location[0] >= image.getWidth()
        || location[1] < 0
        || location[1] >= image.getHeight()) {
      throw new IllegalArgumentException("location should point to a pixel within the image");
    }

    depthFirstFill(image, location, targetColor, replacementColor);
  }

  private static void breadthFirstFill(
      BufferedImage image,
      int[] location,
      Color targetColor,
      Color replacementColor,
      ArrayList<int[]> queue) {
    int[] currentLocation = queue.get(0);
    queue.remove(0);

    if (image.getRGB(currentLocation[0], currentLocation[1]) == targetColor.getRGB()) {
      image.setRGB(currentLocation[0], currentLocation[1], replacementColor.getRGB());

      for (int i = 0; i < neighbors.length; i++) {
        int x = currentLocation[0] + neighbors[i][0];
        int y = currentLocation[1] + neighbors[i][1];
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
          queue.add(new int[] {x, y});
        }
      }
    }
  }

  private static void depthFirstFill(
      BufferedImage image, int[] location, Color targetColor, Color replacementColor) {
    if (image.getRGB(location[0], location[1]) == targetColor.getRGB()) {
      image.setRGB(location[0], location[1], replacementColor.getRGB());

      for (int i = 0; i < neighbors.length; i++) {
        int x = location[0] + neighbors[i][0];
        int y = location[1] + neighbors[i][1];
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
          depthFirstFill(image, new int[] {x, y}, targetColor, replacementColor);
        }
      }
    }
  }

  private static BufferedImage generateTestImage() {
    Color[][] layout = {
      {violet, violet, green, green, black, green, green},
      {violet, green, green, black, green, green, green},
      {green, green, green, black, green, green, green},
      {black, black, green, black, white, white, green},
      {violet, violet, black, violet, violet, white, white},
      {green, green, green, violet, violet, violet, violet},
      {violet, violet, violet, violet, violet, violet, violet}
    };

    BufferedImage image = new BufferedImage(7, 7, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < layout.length; x++) {
      for (int y = 0; y < layout[x].length; y++) {
        image.setRGB(x, y, layout[y][x].getRGB());
      }
    }

    return image;
  }

  private static void testBreadthFirst(
      int[] fillLocation,
      Color targetColor,
      Color replacementColor,
      int[] testLocation,
      Color expectedColor) {
    BufferedImage image = generateTestImage();
    breadthFirstSearch(image, fillLocation, targetColor, replacementColor);
    assert image.getRGB(testLocation[0], testLocation[1]) == expectedColor.getRGB();
  }

  private static void testDepthFirst(
      int[] fillLocation,
      Color targetColor,
      Color replacementColor,
      int[] testLocation,
      Color expectedColor) {
    BufferedImage image = generateTestImage();
    depthFirstSearch(image, fillLocation, targetColor, replacementColor);
    assert image.getRGB(testLocation[0], testLocation[1]) == expectedColor.getRGB();
  }
}
