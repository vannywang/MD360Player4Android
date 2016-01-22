package com.asha.md360player4android;

import android.content.Context;
import android.opengl.GLES20;

import com.asha.md360player4android.common.RawResourceReader;
import com.asha.md360player4android.common.ShaderHelper;

/**
 * Created by hzqiujiadi on 16/1/22.
 * hzqiujiadi ashqalcn@gmail.com
 */
public class MD360Program {
    private int mMVPMatrixHandle;
    private int mMVMatrixHandle;

    private int mTextureUniformHandle;
    private int mPositionHandle;
    private int mTextureCoordinateHandle;
    private int mProgramHandle;

    public MD360Program() {
    }

    public void build(Context context){
        final String vertexShader = getVertexShader(context);
        final String fragmentShader = getFragmentShader(context);

        final int vertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, vertexShader);
        final int fragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);

        mProgramHandle = ShaderHelper.createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle,
                new String[] {"a_Position", "a_TexCoordinate"});

        // Set program handles for cube drawing.
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVPMatrix");
        mMVMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVMatrix");
        mTextureUniformHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_Texture");
        mPositionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Position");
        mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_TexCoordinate");
    }

    protected String getVertexShader(Context context){
        return RawResourceReader.readTextFileFromRawResource(context, R.raw.per_pixel_vertex_shader);
    }

    protected String getFragmentShader(Context context){
        return RawResourceReader.readTextFileFromRawResource(context, R.raw.per_pixel_fragment_shader);
    }

    public void use() {
        GLES20.glUseProgram(mProgramHandle);
    }

    public int getMVPMatrixHandle() {
        return mMVPMatrixHandle;
    }

    public int getMVMatrixHandle() {
        return mMVMatrixHandle;
    }

    public int getTextureUniformHandle() {
        return mTextureUniformHandle;
    }

    public int getPositionHandle() {
        return mPositionHandle;
    }

    public int getTextureCoordinateHandle() {
        return mTextureCoordinateHandle;
    }
}
