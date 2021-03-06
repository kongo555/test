package com.fluid.stam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by kongo on 06.02.16.
 */
public class StamRenderer {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private int width;
    private int height;
    int n = 64;

    private Vector3 mouse_position = new Vector3(0,0,0);
    private boolean drawVel;
    Stam stam;

    public StamRenderer() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        stam = new Stam(width, height);
    }

    public void render() {
        camera.update();
        mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mouse_position);

        stam.update(0.4f, mouse_position);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		Gdx.gl.glEnable(GL20.GL_BLEND);
//		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


        shapeRenderer.setProjectionMatrix(camera.combined);
        if(Gdx.input.isKeyJustPressed(Input.Keys.V))
            drawVel = !drawVel;
        stam.render(shapeRenderer, drawVel);
//		shapeRenderer.begin(ShapeType.Line);
//		renderGrid();
//		shapeRenderer.end();
    }

    private void renderGrid() {
        float cellHeight = height / n;
        float cellWidth = width / n;
        shapeRenderer.setColor(Color.BLACK);
        for (int i = 1; i < n; i++) {
            shapeRenderer.line(0, cellHeight * i, width, cellHeight * i);
            shapeRenderer.line(cellWidth * i, 0, cellWidth * i, height);
        }
    }
}
