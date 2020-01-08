public class Terning {

        private static final int MAX	= 6;
        private int faceValue;

        public Terning()
        {
            roll();
        }

        public void roll()
        {
            int Die1 = (int) ( ( Math.random(  ) * MAX ) + 1 );
            int Die2 = (int) ( ( Math.random(  ) * MAX ) + 1 );
            faceValue = Die1+Die2;
        }

        public int getFaceValue()
        {
            return faceValue;
        }
    }


