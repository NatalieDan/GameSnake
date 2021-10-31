import java.awt.Color;
import java.util.Random;

public class SuperFood extends Cell{
    private Random random;
    private Snake snake;
    private Poison poison;
    private Food food;

    public SuperFood(Snake snake){
        super(-1,-1, GameSnake.CELL_SIZE, GameSnake.SUPERFOOD_COLOR);
        random = new Random();
        this.snake = snake;
    }

    public void setPoison(Poison poison){
        this.poison = poison;
    }

    public boolean isSuperFood(int x, int y){
        return (getX() == x) && (getY() == y);
    }

    public boolean isEaten(){
        return getX() == -1;
    }

    public void eat(){
        set(-1, -1);
    }

    public void appear(){
        int x, y, w;
        w = random.nextInt(5);
        if (w == 1) {
            do {
                x = random.nextInt(GameSnake.CANVAS_WIDTH);
                y = random.nextInt(GameSnake.CANVAS_HEIGHT);
            } while (snake.isInSnake(x, y));
            set(x, y);
        } else{
            set(-2,-2);
        }
    }
}
