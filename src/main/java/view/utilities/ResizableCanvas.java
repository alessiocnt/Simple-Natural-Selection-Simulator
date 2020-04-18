package view.utilities;

import javafx.scene.canvas.Canvas;

/**
 * Modification for making the Canvas resizable.
 *
 */
public class ResizableCanvas extends Canvas {

    @Override
    public final boolean isResizable() {
        return true;
    }

    @Override
    public final double maxHeight(final double width) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public final double maxWidth(final double height) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public final double minWidth(final double height) {
        return 1D;
    }

    @Override
    public final double minHeight(final double width) {
        return 1D;
    }

    @Override
    public final void resize(final double width, final double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
}
