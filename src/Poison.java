import java.awt.*;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Poison {
    private LinkedList<Cell> poison;
    private Food food;
    private Snake snake;
    private Random random;
    private SuperFood superFood;

    public Poison(int x, int y, Snake snake){
        poison = new LinkedList<>();
        poison.add(new Cell(x, y, GameSnake.CELL_SIZE, GameSnake.SNAKE_COLOR));
        this.snake = snake;
        random = new Random();
    }

    public int size(){
        return poison.size();
    }

    public void delete(){
        poison.clear();
    }

    public void setFood(Food food){
        this.food = food;
    }

    public void setSuperFood(SuperFood superFood){
        this.superFood = superFood;
    }

    public void paint(Graphics2D g){
        for (Cell cell : poison){
            cell.paint(g);
        }
    }

    public void plus(){
        int x, y;
        do {
            x = random.nextInt(GameSnake.CANVAS_WIDTH);
            y = random.nextInt(GameSnake.CANVAS_HEIGHT);
        } while (snake.isInSnake(x, y) || isPoison(x,y) || food.isFood(x, y) || superFood.isSuperFood(x, y));
        poison.addFirst(new Cell(x, y, GameSnake.CELL_SIZE, GameSnake.POISON_COLOR));
    }

    public boolean isPoison(int x, int y){
        for (Cell cell : poison){
            if ((cell.getX() == x) && (cell.getY() == y)){
                return true;
            }
        }
        return false;
    }

}
