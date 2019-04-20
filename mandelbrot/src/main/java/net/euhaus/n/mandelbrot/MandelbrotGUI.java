package net.euhaus.n.mandelbrot;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MandelbrotGUI extends Application {

    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 600;

    private Mandelbrot mandelbrot;

    private static final Map<Integer, Color> colorMap;

    static {
        colorMap = new HashMap<>();
        colorMap.put(0, new Color(0.0, 0.0, 0.0, 0.0));
        colorMap.put(1, new Color(0.0322580645161, 0.108786984361, 0.00539348773401, 0.111969722919));
        colorMap.put(2, new Color(0.0645161290323, 0.151646272438, 0.00992008216835, 0.207169324221));
        colorMap.put(3, new Color(0.0967741935484, 0.177432471465, 0.0138722350159, 0.291172042908));
        colorMap.put(4, new Color(0.129032258065, 0.197815255105, 0.0181917051769, 0.380334218831));
        colorMap.put(5, new Color(0.161290322581, 0.217212307303, 0.0224490138622, 0.470590341612));
        colorMap.put(6, new Color(0.193548387097, 0.242299326954, 0.0265403684814, 0.55366056478));
        colorMap.put(7, new Color(0.225806451613, 0.246161677012, 0.0316100545371, 0.662773582883));
        colorMap.put(8, new Color(0.258064516129, 0.0391938496819, 0.102059502264, 0.820224013463));
        colorMap.put(9, new Color(0.290322580645, 0.0330077889855, 0.234301565771, 0.684120017099));
        colorMap.put(10, new Color(0.322580645161, 0.027342572762, 0.30516422217, 0.568742692879));
        colorMap.put(11, new Color(0.354838709677, 0.0233214021309, 0.356429314246, 0.486194627807));
        colorMap.put(12, new Color(0.387096774194, 0.0209210625389, 0.399669044317, 0.431618682832));
        colorMap.put(13, new Color(0.41935483871, 0.021456273063, 0.439800086067, 0.392182769224));
        colorMap.put(14, new Color(0.451612903226, 0.0231370820279, 0.479419284367, 0.348429122934));
        colorMap.put(15, new Color(0.483870967742, 0.0254740155568, 0.518770047056, 0.297904142682));
        colorMap.put(16, new Color(0.516129032258, 0.0271421867926, 0.557915014845, 0.239575650389));
        colorMap.put(17, new Color(0.548387096774, 0.0284678047008, 0.596777269798, 0.175458157761));
        colorMap.put(18, new Color(0.58064516129, 0.0312413336822, 0.635206873602, 0.120063295218));
        colorMap.put(19, new Color(0.612903225806, 0.0327448443691, 0.67336887176, 0.0992531115405));
        colorMap.put(20, new Color(0.645161290323, 0.0415383658849, 0.711987208831, 0.0339929543272));
        colorMap.put(21, new Color(0.677419354839, 0.227566481325, 0.74283739542, 0.0359423658082));
        colorMap.put(22, new Color(0.709677419355, 0.374535200277, 0.768415962919, 0.0371934165377));
        colorMap.put(23, new Color(0.741935483871, 0.517234571204, 0.788163270506, 0.0380195489276));
        colorMap.put(24, new Color(0.774193548387, 0.657795610039, 0.802005572972, 0.0391239148551));
        colorMap.put(25, new Color(0.806451612903, 0.796045206502, 0.810122510174, 0.0387729461439));
        colorMap.put(26, new Color(0.838709677419, 0.953317183851, 0.80450939606, 0.046060278241));
        colorMap.put(27, new Color(0.870967741935, 0.98234848296, 0.827339414951, 0.630458909786));
        colorMap.put(28, new Color(0.903225806452, 0.989687135873, 0.866196263885, 0.784178735151));
        colorMap.put(29, new Color(0.935483870968, 0.993982117378, 0.909532650768, 0.87417700771));
        colorMap.put(30, new Color(0.967741935484, 0.997026623438, 0.954589245277, 0.941260238402));
        colorMap.put(31, new Color(1.0, 1.0, 1.0, 1.0));

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(MAX_WIDTH, MAX_HEIGHT);

        final double[] selectionStart = new double[2];

    /*    canvas.setOnMousePressed(e -> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            selectionStart[0] = e.getX();
            selectionStart[1] = e.getY();
            gc.setFill(Color.color(Color.BLUE.getRed(), Color.BLUE.getGreen(), Color.BLUE.getBlue(), 0.2));
        });
        canvas.setOnMouseDragged( e-> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
            gc.fillRect(selectionStart[0], selectionStart[1], e.getX()-selectionStart[0], e.getY()-selectionStart[1]);
        });
        canvas.setOnMouseReleased( e-> {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        });*/

        GraphicsContext gc = canvas.getGraphicsContext2D();

        mandelbrot = new Mandelbrot(-1.8, 0.6, -1.2, 1.2);
        drawMandelbrot(gc);

        root.getChildren().add(canvas);


        Scene scene = new Scene(root);
        scene.setFill(this.getColor(1));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawMandelbrot(GraphicsContext gc) {
        Map<Integer, Set<Complex>> set = mandelbrot.sampleSet();
        System.out.println("Set ready");
        for (Map.Entry<Integer, Set<Complex>> entry : set.entrySet()) {
            int step = entry.getKey();
             gc.setFill(getColor(step));
            for(Complex c : entry.getValue()) {
                double x = ((c.getReal() - mandelbrot.getMinR()) / (mandelbrot.getMaxR() - mandelbrot.getMinR())) * MAX_WIDTH;
                double y = ((c.getImaginary() - mandelbrot.getMinIm()) / (mandelbrot.getMaxIm() - mandelbrot.getMinIm())) * MAX_HEIGHT;
                gc.fillOval(x, y, 1, 1);
            }
        }

    }


    public Color getColor(int step) {
        return colorMap.get(step % (colorMap.size() + 1));
    }

}
