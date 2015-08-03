package com.golab.edgewrite_mvc.models;

/**
 * Created by Ueno on 2015/07/29.
 */
public class EdgeWrite {

    float edgeWidth     = 320; // EdgeWriteでの入力範囲の横幅
    float edgeHeight    = 320; // EdgeWriteでの入力範囲の縦幅
    float leftTopX      = 0;   // EdgeWriteでの入力範囲の左上のx座標
    float leftTopY      = 0;   // EdgeWriteでの入力範囲の左上のy座標

    private static final int LEFT_TOP       = 1; // 左上の領域の番号
    private static final int RIGHT_TOP      = 2; // 右上の領域の番号
    private static final int RIGHT_BOTTOM   = 4; // 右下の領域の番号
    private static final int LEFT_BOTTOM    = 8; // 左下の領域の番号


    public interface OnUpdatedListener {

        void onUpdated(EdgeWrite edgeWrite);

    }

    /**
     * いつでも同じオブジェクトを取得できるようにシングルトンにした．
     */
    public static EdgeWrite getInstance() { return instance; }


    /**
     * 初めのタップで選択したエッジを設定する関数
     * @param x
     * タップされているx座標
     * @param y
     * タップされているy座標
     */
    public void setFirstEdge(float x, float y) {
        int edge = 0;

        // 画面左側
        if(x >= leftTopX && x <= edgeWidth / 2) {

            // かつ下側
            if(y <= edgeHeight && y > edgeHeight / 2) {

                edge = LEFT_BOTTOM;
            }

            // かつ上側
            if(y >= leftTopY && y <= edgeHeight / 2) {

                edge = LEFT_TOP;
            }
        }

        // 画面右側
        else if(x > edgeWidth / 2 && x <= edgeWidth) {

            // かつ下側
            if(y <= edgeHeight && y > edgeHeight / 2) {

                edge = RIGHT_BOTTOM;
            }

            // かつ上側
            if(y >= leftTopY && y <= edgeHeight / 2) {

                edge = RIGHT_TOP;
            }
        }

        this.edgeOrder[ edges ] = edge;
        this.edges++; // 格納されているエッジの数をインクリメント

    }


    /**
     * どのエッジを選択しているかを判別して，配列に格納する関数
     * @param x
     * x座標
     * @param y
     * y座標
     */
    public void setEdge(float x, float y) {

        double k = (edgeHeight / edgeWidth) * (x - leftTopX); // エッジ計算用

        if(edges <= 0 || edges > 30) return; // 初めのエッジの判別はsetFirstEdgeで行う

        if((x <= (leftTopX + edgeWidth)) &&
                (x >= leftTopX) &&
                (y <= (leftTopY + edgeHeight)) &&
                (y >= leftTopY)) { // EdgeWriteの入力範囲内であるかどうか


            // 左下の領域設定
            if((y - leftTopY >= k + (0.5 * edgeHeight)) &&
                    (x - leftTopX < 0.5 * edgeWidth)) {

                if(this.edgeOrder[edges - 1] != LEFT_BOTTOM) {

                    this.edgeOrder[ edges ] = LEFT_BOTTOM;
                    this.edges++;
                }

            }


            // 左上の領域設定
            if((y - leftTopY <= -k + (0.5 * edgeHeight)) &&
                    (x - leftTopX < 0.5 * edgeWidth)) {

                if(this.edgeOrder[edges - 1] != LEFT_TOP) {

                    this.edgeOrder[ edges ] = LEFT_TOP;
                    this.edges++;
                }

            }


            // 右下の領域設定
            if((y - leftTopY >= -k + (1.5 * edgeHeight)) &&
                    (x - leftTopX > 0.5 * edgeWidth)) {

                if(this.edgeOrder[edges - 1] != RIGHT_BOTTOM) {

                    this.edgeOrder[ edges ] = RIGHT_BOTTOM;
                    this.edges++;
                }

            }


            // 右上の領域設定
            if((y - leftTopY <= k - (0.5 * edgeHeight)) &&
                    (x - leftTopX > 0.5 * edgeWidth)) {

                if(this.edgeOrder[edges - 1] != RIGHT_TOP) {

                    this.edgeOrder[ edges ] = RIGHT_TOP;
                    this.edges++;
                }
            }
        }
    }



    /**
     * 指定した添字の場所のエッジを取得する関数
     * @param index
     * 取得したいエッジの添字
     * @return
     * 指定した添字の位置のエッジ番号
     */
    public int getEdge(int index) { return this.edgeOrder[ index ]; }

    public int getOrderSize() { return this.edgeOrder.length; }


    /**
     * コンストラクタ
     */
    private EdgeWrite() {

        this.edges = 0;
        this.edgeOrder = new int[30];

    }


    private static final EdgeWrite instance = new EdgeWrite();
    private int edgeOrder[]; // なぞったエッジの順番を保持
    private int edges; // 格納されているエッジの順番の数

}
