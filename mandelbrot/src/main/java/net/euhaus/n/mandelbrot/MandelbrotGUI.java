package net.euhaus.n.mandelbrot;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.apache.commons.math3.complex.Complex;

import java.util.*;

public class MandelbrotGUI extends Application {

    public static final int MAX_WIDTH = 800;
    public static final int MAX_HEIGHT = 600;

    private Mandelbrot mandelbrot;

    private static final Map<Integer, Color> colorMap = new HashMap<>();

    static {
        List<Color> colors = new ArrayList<>();

        colors.add(new Color(0.001462, 0.000466, 0.013866,1.0));
        colors.add(new Color(0.002267, 0.001270, 0.018570, 1.0));
        colors.add(new Color(0.003299, 0.002249, 0.024239, 1.0));
        colors.add(new Color(0.004547, 0.003392, 0.030909, 1.0));
        colors.add(new Color(0.006006, 0.004692, 0.038558, 1.0));
        colors.add(new Color(0.007676, 0.006136, 0.046836, 1.0));
        colors.add(new Color(0.009561, 0.007713, 0.055143, 1.0));
        colors.add(new Color(0.011663, 0.009417, 0.063460, 1.0));
        colors.add(new Color(0.013995, 0.011225, 0.071862, 1.0));
        colors.add(new Color(0.016561, 0.013136, 0.080282, 1.0));
        colors.add(new Color(0.019373, 0.015133, 0.088767, 1.0));
        colors.add(new Color(0.022447, 0.017199, 0.097327, 1.0));
        colors.add(new Color(0.025793, 0.019331, 0.105930, 1.0));
        colors.add(new Color(0.029432, 0.021503, 0.114621, 1.0));
        colors.add(new Color(0.033385, 0.023702, 0.123397, 1.0));
        colors.add(new Color(0.037668, 0.025921, 0.132232, 1.0));
        colors.add(new Color(0.042253, 0.028139, 0.141141, 1.0));
        colors.add(new Color(0.046915, 0.030324, 0.150164, 1.0));
        colors.add(new Color(0.051644, 0.032474, 0.159254, 1.0));
        colors.add(new Color(0.056449, 0.034569, 0.168414, 1.0));
        colors.add(new Color(0.061340, 0.036590, 0.177642, 1.0));
        colors.add(new Color(0.066331, 0.038504, 0.186962, 1.0));
        colors.add(new Color(0.071429, 0.040294, 0.196354, 1.0));
        colors.add(new Color(0.076637, 0.041905, 0.205799, 1.0));
        colors.add(new Color(0.081962, 0.043328, 0.215289, 1.0));
        colors.add(new Color(0.087411, 0.044556, 0.224813, 1.0));
        colors.add(new Color(0.092990, 0.045583, 0.234358, 1.0));
        colors.add(new Color(0.098702, 0.046402, 0.243904, 1.0));
        colors.add(new Color(0.104551, 0.047008, 0.253430, 1.0));
        colors.add(new Color(0.110536, 0.047399, 0.262912, 1.0));
        colors.add(new Color(0.116656, 0.047574, 0.272321, 1.0));
        colors.add(new Color(0.122908, 0.047536, 0.281624, 1.0));
        colors.add(new Color(0.129285, 0.047293, 0.290788, 1.0));
        colors.add(new Color(0.135778, 0.046856, 0.299776, 1.0));
        colors.add(new Color(0.142378, 0.046242, 0.308553, 1.0));
        colors.add(new Color(0.149073, 0.045468, 0.317085, 1.0));
        colors.add(new Color(0.155850, 0.044559, 0.325338, 1.0));
        colors.add(new Color(0.162689, 0.043554, 0.333277, 1.0));
        colors.add(new Color(0.169575, 0.042489, 0.340874, 1.0));
        colors.add(new Color(0.176493, 0.041402, 0.348111, 1.0));
        colors.add(new Color(0.183429, 0.040329, 0.354971, 1.0));
        colors.add(new Color(0.190367, 0.039309, 0.361447, 1.0));
        colors.add(new Color(0.197297, 0.038400, 0.367535, 1.0));
        colors.add(new Color(0.204209, 0.037632, 0.373238, 1.0));
        colors.add(new Color(0.211095, 0.037030, 0.378563, 1.0));
        colors.add(new Color(0.217949, 0.036615, 0.383522, 1.0));
        colors.add(new Color(0.224763, 0.036405, 0.388129, 1.0));
        colors.add(new Color(0.231538, 0.036405, 0.392400, 1.0));
        colors.add(new Color(0.238273, 0.036621, 0.396353, 1.0));
        colors.add(new Color(0.244967, 0.037055, 0.400007, 1.0));
        colors.add(new Color(0.251620, 0.037705, 0.403378, 1.0));
        colors.add(new Color(0.258234, 0.038571, 0.406485, 1.0));
        colors.add(new Color(0.264810, 0.039647, 0.409345, 1.0));
        colors.add(new Color(0.271347, 0.040922, 0.411976, 1.0));
        colors.add(new Color(0.277850, 0.042353, 0.414392, 1.0));
        colors.add(new Color(0.284321, 0.043933, 0.416608, 1.0));
        colors.add(new Color(0.290763, 0.045644, 0.418637, 1.0));
        colors.add(new Color(0.297178, 0.047470, 0.420491, 1.0));
        colors.add(new Color(0.303568, 0.049396, 0.422182, 1.0));
        colors.add(new Color(0.309935, 0.051407, 0.423721, 1.0));
        colors.add(new Color(0.316282, 0.053490, 0.425116, 1.0));
        colors.add(new Color(0.322610, 0.055634, 0.426377, 1.0));
        colors.add(new Color(0.328921, 0.057827, 0.427511, 1.0));
        colors.add(new Color(0.335217, 0.060060, 0.428524, 1.0));
        colors.add(new Color(0.341500, 0.062325, 0.429425, 1.0));
        colors.add(new Color(0.347771, 0.064616, 0.430217, 1.0));
        colors.add(new Color(0.354032, 0.066925, 0.430906, 1.0));
        colors.add(new Color(0.360284, 0.069247, 0.431497, 1.0));
        colors.add(new Color(0.366529, 0.071579, 0.431994, 1.0));
        colors.add(new Color(0.372768, 0.073915, 0.432400, 1.0));
        colors.add(new Color(0.379001, 0.076253, 0.432719, 1.0));
        colors.add(new Color(0.385228, 0.078591, 0.432955, 1.0));
        colors.add(new Color(0.391453, 0.080927, 0.433109, 1.0));
        colors.add(new Color(0.397674, 0.083257, 0.433183, 1.0));
        colors.add(new Color(0.403894, 0.085580, 0.433179, 1.0));
        colors.add(new Color(0.410113, 0.087896, 0.433098, 1.0));
        colors.add(new Color(0.416331, 0.090203, 0.432943, 1.0));
        colors.add(new Color(0.422549, 0.092501, 0.432714, 1.0));
        colors.add(new Color(0.428768, 0.094790, 0.432412, 1.0));
        colors.add(new Color(0.434987, 0.097069, 0.432039, 1.0));
        colors.add(new Color(0.441207, 0.099338, 0.431594, 1.0));
        colors.add(new Color(0.447428, 0.101597, 0.431080, 1.0));
        colors.add(new Color(0.453651, 0.103848, 0.430498, 1.0));
        colors.add(new Color(0.459875, 0.106089, 0.429846, 1.0));
        colors.add(new Color(0.466100, 0.108322, 0.429125, 1.0));
        colors.add(new Color(0.472328, 0.110547, 0.428334, 1.0));
        colors.add(new Color(0.478558, 0.112764, 0.427475, 1.0));
        colors.add(new Color(0.484789, 0.114974, 0.426548, 1.0));
        colors.add(new Color(0.491022, 0.117179, 0.425552, 1.0));
        colors.add(new Color(0.497257, 0.119379, 0.424488, 1.0));
        colors.add(new Color(0.503493, 0.121575, 0.423356, 1.0));
        colors.add(new Color(0.509730, 0.123769, 0.422156, 1.0));
        colors.add(new Color(0.515967, 0.125960, 0.420887, 1.0));
        colors.add(new Color(0.522206, 0.128150, 0.419549, 1.0));
        colors.add(new Color(0.528444, 0.130341, 0.418142, 1.0));
        colors.add(new Color(0.534683, 0.132534, 0.416667, 1.0));
        colors.add(new Color(0.540920, 0.134729, 0.415123, 1.0));
        colors.add(new Color(0.547157, 0.136929, 0.413511, 1.0));
        colors.add(new Color(0.553392, 0.139134, 0.411829, 1.0));
        colors.add(new Color(0.559624, 0.141346, 0.410078, 1.0));
        colors.add(new Color(0.565854, 0.143567, 0.408258, 1.0));
        colors.add(new Color(0.572081, 0.145797, 0.406369, 1.0));
        colors.add(new Color(0.578304, 0.148039, 0.404411, 1.0));
        colors.add(new Color(0.584521, 0.150294, 0.402385, 1.0));
        colors.add(new Color(0.590734, 0.152563, 0.400290, 1.0));
        colors.add(new Color(0.596940, 0.154848, 0.398125, 1.0));
        colors.add(new Color(0.603139, 0.157151, 0.395891, 1.0));
        colors.add(new Color(0.609330, 0.159474, 0.393589, 1.0));
        colors.add(new Color(0.615513, 0.161817, 0.391219, 1.0));
        colors.add(new Color(0.621685, 0.164184, 0.388781, 1.0));
        colors.add(new Color(0.627847, 0.166575, 0.386276, 1.0));
        colors.add(new Color(0.633998, 0.168992, 0.383704, 1.0));
        colors.add(new Color(0.640135, 0.171438, 0.381065, 1.0));
        colors.add(new Color(0.646260, 0.173914, 0.378359, 1.0));
        colors.add(new Color(0.652369, 0.176421, 0.375586, 1.0));
        colors.add(new Color(0.658463, 0.178962, 0.372748, 1.0));
        colors.add(new Color(0.664540, 0.181539, 0.369846, 1.0));
        colors.add(new Color(0.670599, 0.184153, 0.366879, 1.0));
        colors.add(new Color(0.676638, 0.186807, 0.363849, 1.0));
        colors.add(new Color(0.682656, 0.189501, 0.360757, 1.0));
        colors.add(new Color(0.688653, 0.192239, 0.357603, 1.0));
        colors.add(new Color(0.694627, 0.195021, 0.354388, 1.0));
        colors.add(new Color(0.700576, 0.197851, 0.351113, 1.0));
        colors.add(new Color(0.706500, 0.200728, 0.347777, 1.0));
        colors.add(new Color(0.712396, 0.203656, 0.344383, 1.0));
        colors.add(new Color(0.718264, 0.206636, 0.340931, 1.0));
        colors.add(new Color(0.724103, 0.209670, 0.337424, 1.0));
        colors.add(new Color(0.729909, 0.212759, 0.333861, 1.0));
        colors.add(new Color(0.735683, 0.215906, 0.330245, 1.0));
        colors.add(new Color(0.741423, 0.219112, 0.326576, 1.0));
        colors.add(new Color(0.747127, 0.222378, 0.322856, 1.0));
        colors.add(new Color(0.752794, 0.225706, 0.319085, 1.0));
        colors.add(new Color(0.758422, 0.229097, 0.315266, 1.0));
        colors.add(new Color(0.764010, 0.232554, 0.311399, 1.0));
        colors.add(new Color(0.769556, 0.236077, 0.307485, 1.0));
        colors.add(new Color(0.775059, 0.239667, 0.303526, 1.0));
        colors.add(new Color(0.780517, 0.243327, 0.299523, 1.0));
        colors.add(new Color(0.785929, 0.247056, 0.295477, 1.0));
        colors.add(new Color(0.791293, 0.250856, 0.291390, 1.0));
        colors.add(new Color(0.796607, 0.254728, 0.287264, 1.0));
        colors.add(new Color(0.801871, 0.258674, 0.283099, 1.0));
        colors.add(new Color(0.807082, 0.262692, 0.278898, 1.0));
        colors.add(new Color(0.812239, 0.266786, 0.274661, 1.0));
        colors.add(new Color(0.817341, 0.270954, 0.270390, 1.0));
        colors.add(new Color(0.822386, 0.275197, 0.266085, 1.0));
        colors.add(new Color(0.827372, 0.279517, 0.261750, 1.0));
        colors.add(new Color(0.832299, 0.283913, 0.257383, 1.0));
        colors.add(new Color(0.837165, 0.288385, 0.252988, 1.0));
        colors.add(new Color(0.841969, 0.292933, 0.248564, 1.0));
        colors.add(new Color(0.846709, 0.297559, 0.244113, 1.0));
        colors.add(new Color(0.851384, 0.302260, 0.239636, 1.0));
        colors.add(new Color(0.855992, 0.307038, 0.235133, 1.0));
        colors.add(new Color(0.860533, 0.311892, 0.230606, 1.0));
        colors.add(new Color(0.865006, 0.316822, 0.226055, 1.0));
        colors.add(new Color(0.869409, 0.321827, 0.221482, 1.0));
        colors.add(new Color(0.873741, 0.326906, 0.216886, 1.0));
        colors.add(new Color(0.878001, 0.332060, 0.212268, 1.0));
        colors.add(new Color(0.882188, 0.337287, 0.207628, 1.0));
        colors.add(new Color(0.886302, 0.342586, 0.202968, 1.0));
        colors.add(new Color(0.890341, 0.347957, 0.198286, 1.0));
        colors.add(new Color(0.894305, 0.353399, 0.193584, 1.0));
        colors.add(new Color(0.898192, 0.358911, 0.188860, 1.0));
        colors.add(new Color(0.902003, 0.364492, 0.184116, 1.0));
        colors.add(new Color(0.905735, 0.370140, 0.179350, 1.0));
        colors.add(new Color(0.909390, 0.375856, 0.174563, 1.0));
        colors.add(new Color(0.912966, 0.381636, 0.169755, 1.0));
        colors.add(new Color(0.916462, 0.387481, 0.164924, 1.0));
        colors.add(new Color(0.919879, 0.393389, 0.160070, 1.0));
        colors.add(new Color(0.923215, 0.399359, 0.155193, 1.0));
        colors.add(new Color(0.926470, 0.405389, 0.150292, 1.0));
        colors.add(new Color(0.929644, 0.411479, 0.145367, 1.0));
        colors.add(new Color(0.932737, 0.417627, 0.140417, 1.0));
        colors.add(new Color(0.935747, 0.423831, 0.135440, 1.0));
        colors.add(new Color(0.938675, 0.430091, 0.130438, 1.0));
        colors.add(new Color(0.941521, 0.436405, 0.125409, 1.0));
        colors.add(new Color(0.944285, 0.442772, 0.120354, 1.0));
        colors.add(new Color(0.946965, 0.449191, 0.115272, 1.0));
        colors.add(new Color(0.949562, 0.455660, 0.110164, 1.0));
        colors.add(new Color(0.952075, 0.462178, 0.105031, 1.0));
        colors.add(new Color(0.954506, 0.468744, 0.099874, 1.0));
        colors.add(new Color(0.956852, 0.475356, 0.094695, 1.0));
        colors.add(new Color(0.959114, 0.482014, 0.089499, 1.0));
        colors.add(new Color(0.961293, 0.488716, 0.084289, 1.0));
        colors.add(new Color(0.963387, 0.495462, 0.079073, 1.0));
        colors.add(new Color(0.965397, 0.502249, 0.073859, 1.0));
        colors.add(new Color(0.967322, 0.509078, 0.068659, 1.0));
        colors.add(new Color(0.969163, 0.515946, 0.063488, 1.0));
        colors.add(new Color(0.970919, 0.522853, 0.058367, 1.0));
        colors.add(new Color(0.972590, 0.529798, 0.053324, 1.0));
        colors.add(new Color(0.974176, 0.536780, 0.048392, 1.0));
        colors.add(new Color(0.975677, 0.543798, 0.043618, 1.0));
        colors.add(new Color(0.977092, 0.550850, 0.039050, 1.0));
        colors.add(new Color(0.978422, 0.557937, 0.034931, 1.0));
        colors.add(new Color(0.979666, 0.565057, 0.031409, 1.0));
        colors.add(new Color(0.980824, 0.572209, 0.028508, 1.0));
        colors.add(new Color(0.981895, 0.579392, 0.026250, 1.0));
        colors.add(new Color(0.982881, 0.586606, 0.024661, 1.0));
        colors.add(new Color(0.983779, 0.593849, 0.023770, 1.0));
        colors.add(new Color(0.984591, 0.601122, 0.023606, 1.0));
        colors.add(new Color(0.985315, 0.608422, 0.024202, 1.0));
        colors.add(new Color(0.985952, 0.615750, 0.025592, 1.0));
        colors.add(new Color(0.986502, 0.623105, 0.027814, 1.0));
        colors.add(new Color(0.986964, 0.630485, 0.030908, 1.0));
        colors.add(new Color(0.987337, 0.637890, 0.034916, 1.0));
        colors.add(new Color(0.987622, 0.645320, 0.039886, 1.0));
        colors.add(new Color(0.987819, 0.652773, 0.045581, 1.0));
        colors.add(new Color(0.987926, 0.660250, 0.051750, 1.0));
        colors.add(new Color(0.987945, 0.667748, 0.058329, 1.0));
        colors.add(new Color(0.987874, 0.675267, 0.065257, 1.0));
        colors.add(new Color(0.987714, 0.682807, 0.072489, 1.0));
        colors.add(new Color(0.987464, 0.690366, 0.079990, 1.0));
        colors.add(new Color(0.987124, 0.697944, 0.087731, 1.0));
        colors.add(new Color(0.986694, 0.705540, 0.095694, 1.0));
        colors.add(new Color(0.986175, 0.713153, 0.103863, 1.0));
        colors.add(new Color(0.985566, 0.720782, 0.112229, 1.0));
        colors.add(new Color(0.984865, 0.728427, 0.120785, 1.0));
        colors.add(new Color(0.984075, 0.736087, 0.129527, 1.0));
        colors.add(new Color(0.983196, 0.743758, 0.138453, 1.0));
        colors.add(new Color(0.982228, 0.751442, 0.147565, 1.0));
        colors.add(new Color(0.981173, 0.759135, 0.156863, 1.0));
        colors.add(new Color(0.980032, 0.766837, 0.166353, 1.0));
        colors.add(new Color(0.978806, 0.774545, 0.176037, 1.0));
        colors.add(new Color(0.977497, 0.782258, 0.185923, 1.0));
        colors.add(new Color(0.976108, 0.789974, 0.196018, 1.0));
        colors.add(new Color(0.974638, 0.797692, 0.206332, 1.0));
        colors.add(new Color(0.973088, 0.805409, 0.216877, 1.0));
        colors.add(new Color(0.971468, 0.813122, 0.227658, 1.0));
        colors.add(new Color(0.969783, 0.820825, 0.238686, 1.0));
        colors.add(new Color(0.968041, 0.828515, 0.249972, 1.0));
        colors.add(new Color(0.966243, 0.836191, 0.261534, 1.0));
        colors.add(new Color(0.964394, 0.843848, 0.273391, 1.0));
        colors.add(new Color(0.962517, 0.851476, 0.285546, 1.0));
        colors.add(new Color(0.960626, 0.859069, 0.298010, 1.0));
        colors.add(new Color(0.958720, 0.866624, 0.310820, 1.0));
        colors.add(new Color(0.956834, 0.874129, 0.323974, 1.0));
        colors.add(new Color(0.954997, 0.881569, 0.337475, 1.0));
        colors.add(new Color(0.953215, 0.888942, 0.351369, 1.0));
        colors.add(new Color(0.951546, 0.896226, 0.365627, 1.0));
        colors.add(new Color(0.950018, 0.903409, 0.380271, 1.0));
        colors.add(new Color(0.948683, 0.910473, 0.395289, 1.0));
        colors.add(new Color(0.947594, 0.917399, 0.410665, 1.0));
        colors.add(new Color(0.946809, 0.924168, 0.426373, 1.0));
        colors.add(new Color(0.946392, 0.930761, 0.442367, 1.0));
        colors.add(new Color(0.946403, 0.937159, 0.458592, 1.0));
        colors.add(new Color(0.946903, 0.943348, 0.474970, 1.0));
        colors.add(new Color(0.947937, 0.949318, 0.491426, 1.0));
        colors.add(new Color(0.949545, 0.955063, 0.507860, 1.0));
        colors.add(new Color(0.951740, 0.960587, 0.524203, 1.0));
        colors.add(new Color(0.954529, 0.965896, 0.540361, 1.0));
        colors.add(new Color(0.957896, 0.971003, 0.556275, 1.0));
        colors.add(new Color(0.961812, 0.975924, 0.571925, 1.0));
        colors.add(new Color(0.966249, 0.980678, 0.587206, 1.0));
        colors.add(new Color(0.971162, 0.985282, 0.602154, 1.0));
        colors.add(new Color(0.976511, 0.989753, 0.616760, 1.0));
        colors.add(new Color(0.982257, 0.994109, 0.631017, 1.0));
        colors.add(new Color(0.988362, 0.998364, 0.644924, 1.0));

        int index = 0;
        for (Color c : colors) {
            colorMap.put(index++, c);
        }

    }

    public MandelbrotGUI() {
        mandelbrot = new Mandelbrot(-2, 1, -1.5, 1.5);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("\uD835\uDD44andelbrot - Timo Neuhaus, B.Sc.");

        Group root = new Group();
        MandelbrotCanvas mandelbrotCanvas = new MandelbrotCanvas();
        mandelbrotCanvas.widthProperty().bind(primaryStage.widthProperty());
        mandelbrotCanvas.heightProperty().bind(primaryStage.heightProperty());

        SelectionCanvas selectionCanvas = new SelectionCanvas();
        selectionCanvas.widthProperty().bind(primaryStage.widthProperty());
        selectionCanvas.heightProperty().bind(primaryStage.heightProperty());

        selectionCanvas.addZoomRequestListener((minR, maxR, minIm, maxIm) -> {
            mandelbrot = new Mandelbrot(minR, maxR, minIm, maxIm);
            mandelbrotCanvas.draw();
        });

        root.getChildren().add(mandelbrotCanvas);
        root.getChildren().add(selectionCanvas);
        Scene scene = new Scene(root);
        scene.setFill(this.getColor(1));
        primaryStage.setScene(scene);
        primaryStage.setWidth(MAX_WIDTH);
        primaryStage.setHeight(MAX_HEIGHT);
        primaryStage.show();
    }

    public Color getColor(int step) {
        return colorMap.get(step % (colorMap.size() + 1));
    }

    public static double rescale(double coordinate, double currentMaxValue, double minNewScape, double maxNewScape) {
        return (1.0 * coordinate / currentMaxValue) * (maxNewScape - minNewScape) + minNewScape;
    }

    private abstract class ResizableCanvas extends Canvas {

        public ResizableCanvas() {
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> draw());
            heightProperty().addListener(evt -> draw());
        }

        public void draw() {
            double width = getWidth();
            double height = getHeight();

            GraphicsContext gc = getGraphicsContext2D();
            gc.clearRect(0, 0, width, height);

            draw(gc);
        }

        protected abstract void draw(GraphicsContext gc);

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) {
            return getWidth();
        }

        @Override
        public double prefHeight(double width) {
            return getHeight();
        }

    }

    private class MandelbrotCanvas extends ResizableCanvas {

        @Override
        protected void draw(GraphicsContext gc) {
            double width = getWidth();
            double height = getHeight();
            Map<Integer, Collection<double[]>> pixels = new HashMap<>();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    double real = rescale(x, width, mandelbrot.getMinR(), mandelbrot.getMaxR());
                    double imaginary = rescale(y, height, mandelbrot.getMinIm(), mandelbrot.getMaxIm());
                    Complex c = new Complex(real, imaginary);
                    int step = mandelbrot.getConvergence(c);
                    pixels.putIfAbsent(step, new LinkedList<>());
                    pixels.get(step).add(new double[]{x, y});

                }
            }

            for (Map.Entry<Integer, Collection<double[]>> entry : pixels.entrySet()) {
                int step = entry.getKey();
                gc.setFill(getColor(step));
                for (double[] coords : entry.getValue()) {
                    gc.fillOval(coords[0], coords[1], 1, 1);
                }
            }
        }
    }

    private class SelectionCanvas extends ResizableCanvas {

        private Double x;

        private Double y;

        private Double currentX;

        private Double currentY;

        private List<ZoomEventListener> zoomRequestListener;

        private double[] rectangle;

        public SelectionCanvas() {
            super();
            zoomRequestListener = new ArrayList<>();
            setOnMousePressed(e -> {
                x = e.getX();
                y = e.getY();
            });
            setOnMouseDragged(e -> {
                currentX = e.getX();
                currentY = e.getY();
                super.draw();
            });
            setOnMouseReleased(e -> {
                zoomRequestListener.forEach(ev -> {
                    double width = widthProperty().get();
                    double height = heightProperty().get();

                    double minR = rescale(rectangle[0], width, mandelbrot.getMinR(), mandelbrot.getMaxR());
                    double minIm = rescale(rectangle[1], height, mandelbrot.getMinIm(), mandelbrot.getMaxIm());

                    double maxR = rescale(rectangle[0] + rectangle[2], width, mandelbrot.getMinR(), mandelbrot.getMaxR());
                    double maxIm = rescale(rectangle[1] + rectangle[3], height, mandelbrot.getMinIm(), mandelbrot.getMaxIm());

                    ev.onZoomRequested(minR, maxR, minIm, maxIm);
                });
                reset();
                super.draw();
            });
        }

        public void addZoomRequestListener(ZoomEventListener listener) {
            zoomRequestListener.add(listener);
        }

        private void reset() {
            x = y = currentY = currentY = null;
            rectangle = null;
        }

        @Override
        protected void draw(GraphicsContext gc) {
            if (x != null && y != null & currentX != null && currentY != null) {
                double deltaX = currentX - x;
                double deltaY = currentY - y;
                gc.setFill(Color.WHITE);
                gc.setGlobalAlpha(0.8);

                deltaX = widthProperty().get() / heightProperty().get() * deltaY;


                gc.fillRect(x, y, deltaX, deltaY);
                rectangle = new double[]{x, y, deltaX, deltaY};
            }
        }
    }

    public interface ZoomEventListener extends EventListener {

        public void onZoomRequested(double minR, double maxR, double minIm, double maxIm);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
