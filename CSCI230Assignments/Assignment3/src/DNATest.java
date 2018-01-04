import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by joshuaglass on 3/1/16.
 */
public class DNATest extends TestCase
{
   private DNA dna;
   private DNA dna1;
   private DNA dna2;

   private String dnaSeq = "ATCGGCTAAT";
   private String bp1 = "AT";
   private String bp2 = "TA";
   private String bp3 = "GC";
   private String bp4 = "CG";

   /**
    * Sets up the test fixture.
    *
    * Called before every test case method.
    */
   @Before
   public void setUp()
   {
      dna = new DNA();

      dna.insert(0, bp1);
      dna.insert(0, bp3);
      dna.insert(0, bp2);
      dna.insert(0, bp2);
   }

   @After
   public void tearDown() throws Exception
   {
      dna = null;
   }

   /**
    * Default constructor for test class DNATest
    */
   /*public DNATest()
   {
      dna = new DNA();

      dna.insert(0, bp1);
      dna.insert(0, bp3);
      dna.insert(0, bp2);
      dna.insert(0, bp2);

   }*/
   @Test
   public void testIsEmpty() throws Exception
   {

   }

   @Test
   public void testClear() throws Exception
   {
      assertFalse(dna.isEmpty());
      dna.clear();
      assertFalse(!dna.isEmpty());
   }

   public void testRemove() throws Exception
   {

   }

   public void testPrintLeft() throws Exception
   {

   }

   public void testPrintRight() throws Exception
   {

   }

   public void testPrintBasePair() throws Exception
   {

   }

   public void testPrint() throws Exception
   {

   }

   public void testFind() throws Exception
   {

   }

   public void testInsertSequence() throws Exception
   {

   }

   public void testRemoveSequence() throws Exception
   {

   }

   public void testFindSequence() throws Exception
   {

   }
}