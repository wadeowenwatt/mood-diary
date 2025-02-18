package wade.owen.watt.mood_diary.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView

class CurvedBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {
    val mPath: Path = Path()
    val mPaint: Paint = Paint()


    private val CURVE_CIRCLE_RADIUS: Int = 256 / 2
    private var mFirstCurveStartPoint: Point = Point()
    private var mFirstCurveEndPoint: Point = Point()
    private var mFirstCurveControlPoint1: Point = Point()
    private var mFirstCurveControlPoint2: Point = Point()

    private var mSecondCurveStartPoint: Point = Point()
    private var mSecondCurveEndPoint: Point = Point()
    private var mSecondCurveControlPoint1: Point = Point()
    private var mSecondCurveControlPoint2: Point = Point()

    init {
        init()
    }

    private fun init() {
        mPaint.apply {
            style = Paint.Style.FILL_AND_STROKE
            color = Color.WHITE
        }
        setBackgroundColor(Color.TRANSPARENT)
    }

    @Override
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    @Override
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val mNavigationBarWidth: Int = width
        val mNavigationBarHeight: Int = height

        mFirstCurveStartPoint.set((mNavigationBarWidth / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3), 0);
        // the coordinates (x,y) of the end point after curve
        mFirstCurveEndPoint.set(mNavigationBarWidth / 2, CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4));
        // same thing for the second curve
        mSecondCurveStartPoint = mFirstCurveEndPoint;
        mSecondCurveEndPoint.set((mNavigationBarWidth / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3), 0);

        // the coordinates (x,y)  of the 1st control point on a cubic curve
        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4), mFirstCurveStartPoint.y);
        // the coordinates (x,y)  of the 2nd control point on a cubic curve
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS, mFirstCurveEndPoint.y);

        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS, mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - (CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4)), mSecondCurveEndPoint.y);

        mPath.reset();
        mPath.moveTo(0f, 0f);
        mPath.lineTo(mFirstCurveStartPoint.x.toFloat(), mFirstCurveStartPoint.y.toFloat());

        mPath.cubicTo(mFirstCurveControlPoint1.x.toFloat(), mFirstCurveControlPoint1.y.toFloat(),
            mFirstCurveControlPoint2.x.toFloat(), mFirstCurveControlPoint2.y.toFloat(),
            mFirstCurveEndPoint.x.toFloat(), mFirstCurveEndPoint.y.toFloat());

        mPath.cubicTo(mSecondCurveControlPoint1.x.toFloat(), mSecondCurveControlPoint1.y.toFloat(),
            mSecondCurveControlPoint2.x.toFloat(), mSecondCurveControlPoint2.y.toFloat(),
            mSecondCurveEndPoint.x.toFloat(), mSecondCurveEndPoint.y.toFloat());

        mPath.lineTo(mNavigationBarWidth.toFloat(), 0f);
        mPath.lineTo(mNavigationBarWidth.toFloat(), mNavigationBarHeight.toFloat());
        mPath.lineTo(0f, mNavigationBarHeight.toFloat());
        mPath.close();
    }

    @Override
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath, mPaint)
    }
}