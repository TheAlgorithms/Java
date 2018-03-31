import java.util.Collections;
import java.util.Comparator;

World world;
Pathfinder p;

void setup()
{
  size(500, 500);
  world = new World();
  p = new Pathfinder(world);
  p.findPath();
}

void draw()
{
  world.update();
}

abstract class Tile
{
   public int xPos;
   public int yPos;
   private int realX;
   private int realY;
   public boolean walkable;
   public color tileColor;
   public NodeState state;
   public Tile lastTile;
   public boolean firstTile;
   
   
   public Tile(int x, int y, boolean w)
   {
     xPos = x;
     yPos = y;
     
     realX = x * 100;
     realY = y * 100;
     
     walkable = w;
     
     tileColor = (walkable) ? color(255, 255, 255) : color(0, 0, 0);
     
     state = NodeState.Untested;
   }
   
   public void update()
   {
      fill(tileColor);
      rect(realX, realY, 100, 100);
   }
   
   public float g()
   {
     if(firstTile)
       return 0.0f;
     else
       return pythag(this.xPos, this.lastTile.xPos, this.yPos, this.lastTile.yPos) + this.lastTile.g();
   }
   
   public float h(Tile endTile)
   {
       return pythag(this.xPos, endTile.xPos, this.yPos, endTile.yPos);
   }
   
   public float f(Tile endTile)
   {
      return g() + h(endTile); 
   }
   
   public float pythag(float x1, float x2, float y1, float y2)
   {
       return sqrt(sq(x1 - x2) + sq(y1 - y2));
   }
}

public enum NodeState
{
   Untested, Open, Closed
}

class WalkableTile extends Tile
{
   public WalkableTile(int x, int y)
   {
      super(x, y, true); 
   }
}

class Obstacle extends Tile
{
   public Obstacle(int x, int y)
   {
      super(x, y, false); 
   }
}

class World
{
   Tile[][] map;
   
   int startX = 0;
   int startY = 0;
   int endX = 4;
   int endY = 0;
   
   int maxX = 5;
   int maxY = 5;
   
   public World()
   {
      map = new Tile[maxX][maxY];
      
      for(int i = 0; i < 5; i++)
      {
        for(int j = 0; j < 3; j++)
        {
           map[j][i] = new WalkableTile(j, i);
        }
      }
      
      for(int i = 0; i < 5; i++)
      {
         map[3][i] = new Obstacle(3, i); 
      }
      
      map[3][4] = new WalkableTile(3, 4);
      //map[3][4] = new Obstacle(3, 4);
      
      for(int i = 0; i < 5; i++)
      {
         map[4][i] = new WalkableTile(4, i);
      }  
      
      map[startX][startY].tileColor =  color(255, 0, 0);
      map[startX][startY].state = NodeState.Open;
      map[startX][startY].firstTile = true;
      
      map[endX][endY].tileColor = color(0, 0, 255);
   }
   
   public void update()
   {
     for(int i = 0; i < 5; i++)
     {
        for(int j = 0; j < 5; j++)
        {
           map[i][j].update(); 
        }
     }
   }
   
   public Tile tileAt(int x, int y)
   {
      return map[x][y]; 
   }
   
   public ArrayList<Tile> adjacentTiles(Tile currentTile)
   {
      ArrayList<Tile> tiles = new ArrayList<Tile>();
      
      for(int i = currentTile.xPos - 1; i < currentTile.xPos + 2; i++)
      {
         for(int j = currentTile.yPos - 1;  j < currentTile.yPos + 2; j++)
         {
            if(i >= 0 && j >= 0 && i < maxX && j < maxY && tileAt(i,j) != currentTile)
            {
               tiles.add(tileAt(i, j)); 
            }
         }
      }
      
      println("New Search");
      
      for(Tile t : tiles)
      {
        println("X = " + t.xPos + ", Y = " + t.yPos); 
      }
      
      return tiles;
   }
   
   public ArrayList<Tile> adjacentWalkableTiles(Tile currentTile)
   {
      ArrayList<Tile> tiles = adjacentTiles(currentTile);
      ArrayList<Tile> nextTiles = new ArrayList<Tile>();
      
      for(Tile t : tiles)
      {
          if(t.walkable && t.state != NodeState.Closed)
          {
            if(t.state == NodeState.Open)
            {
              float cost = t.pythag(t.xPos, t.lastTile.xPos, t.yPos, t.lastTile.yPos);
              float tempG = currentTile.g() + cost;
              
              if(tempG < t.g())
              {
                t.lastTile = currentTile;
                nextTiles.add(t);
              }
            }
            else
            {
              t.lastTile = currentTile;
              t.state = NodeState.Open;
              nextTiles.add(t);
            }
          }
      }
      
      println("New Elimination");
      
      for(Tile t : nextTiles)
      {
        println("X = " + t.xPos + ", Y = " + t.yPos); 
        t.tileColor = color(50, 50, 50);
      }
      
      return nextTiles;
   } 
}

class Pathfinder
{
   Tile startTile;
   Tile endTile;
   World world;
   
   Pathfinder(World w)
   {
      world = w;
      
      startTile = world.tileAt(world.startX, world.startY);
      endTile = world.tileAt(world.endX, world.endY);
   }
   
   public boolean Search(Tile currentTile)
   {
     currentTile.state = NodeState.Closed;
     ArrayList<Tile> tiles = world.adjacentWalkableTiles(currentTile);
     
     Collections.sort(tiles, new Comparator<Tile>()
     {
       @Override
       public int compare(Tile t1, Tile t2)
       {
         return Float.compare(t1.f(endTile), t2.f(endTile));
       }
     });
     
     println("New Sort");
     for(Tile t : tiles)
     {
         println("F = " + t.f(endTile));
     }
     
     for(Tile t : tiles)
     {
         if(t.xPos == endTile.xPos && t.yPos == endTile.yPos)
         {
            print("Found End");
            return true; 
         }
         else
         {
            if(Search(t))
            {
               return true; 
            }
         }
     }
     
     return false;
   }
   
   public void findPath()
   {
     ArrayList<Tile> path = new ArrayList<Tile>();
     
     boolean success = Search(startTile);
     
     if(success)
     {
        Tile t = this.endTile;
        
        while(t.lastTile != null)
        {
          path.add(t);
          t.tileColor = color(100, 100, 100);
          t = t.lastTile;
        }
        
        this.endTile.tileColor = color(0, 0, 255);
     }
     else
     {
        print("Cannot Find End"); 
     }
   }
}