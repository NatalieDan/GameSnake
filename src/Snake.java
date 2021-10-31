import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> snake;
    private int direction;
    private Food food;
    private Poison poison;
    private SuperFood superFood;
    private int speed;

    public Snake(int x, int y, int length, int direction){
        snake = new LinkedList<>();
        for (int i=0; i<length; i++){
            snake.add(new Cell(x-i, y, GameSnake.CELL_SIZE, GameSnake.SNAKE_COLOR));
        }
        this.direction = direction;
        speed = 0;
    }

    public void setFood(Food food){
        this.food = food;
    }

    public void setSuperFood(SuperFood superFood){
        this.superFood = superFood;
    }

    public void setPoison(Poison poison){
        this.poison = poison;
    }

    public void setDirection(int direction){
        if (this.direction == direction){
            speed = 1;
        }
        if ((direction >= GameSnake.KEY_LEFT) && (direction <= GameSnake.KEY_DOWN)){
            if (Math.abs(this.direction - direction) != 2){
                this.direction = direction;
            }
        }
    }

    public int getSpeed(){
        return speed;
    }

    public void paint(Graphics2D g){
        for (Cell cell : snake){
            cell.paint(g);
        }
    }

    public void move(){
        int x = snake.getFirst().getX();
        int y = snake.getFirst().getY();
        int x2 = x;
        int y2 = y;
        switch (direction){
            case GameSnake.KEY_LEFT:
                x--;
                x2 = x - 1;
                if (x<0) {
                    x = GameSnake.CANVAS_WIDTH - 1;
                    x2 = x - 1;
                }
                break;
            case GameSnake.KEY_RIGHT:
                x++;
                x2 = x + 1;
                if (x == GameSnake.CANVAS_WIDTH) {
                    x = 0;
                    x2 = x + 1;
                }
                break;
            case GameSnake.KEY_UP:
                y--;
                y2 = y - 1;
                if (y < 0) {
                    y = GameSnake.CANVAS_HEIGHT - 1;
                    y2 = y - 1;
                }
                break;
            case GameSnake.KEY_DOWN:
                y++;
                y2 = y + 1;
                if (y == GameSnake.CANVAS_HEIGHT) {
                    y = 0;
                    y2 = y + 1;
                }
                break;
        }
        if (isInSnake(x, y) || poison.isPoison(x, y)) {
            GameSnake.gameOver = true;
            return;
        }
        snake.addFirst(new Cell(x, y, GameSnake.CELL_SIZE, GameSnake.SNAKE_COLOR));
        if(food.isFood(x, y)){
            food.eat();
        } else if (superFood.isSuperFood(x, y)){
            superFood.eat();
            snake.addFirst(new Cell(x2, y2, GameSnake.CELL_SIZE, GameSnake.SNAKE_COLOR));
            poison.delete();
        } else {
            snake.removeLast();
        }
    }

    public int size(){
        return snake.size();
    }

    public boolean isInSnake(int x, int y){
        for (Cell cell : snake){
            if ((cell.getX() == x) && (cell.getY() == y)){
                return true;
            }
        }
        return false;
    }

}
