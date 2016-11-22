package com.intellij.diff;

import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.diff.FilesTooBigForDiffException;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * author: lesya
 */

public class SelectedBlockHistoryTest extends TestCase {

  public void test1() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"4", "52", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test2() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"4", "52", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test3() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test4() throws FilesTooBigForDiffException {
    doTest(
        new String[]{},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test5() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{},

        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test6() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test7() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{}
    );
  }

  public void test8() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"44", "4", "5", "6", "66"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test9() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"44", "4", "55", "6", "66"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test10() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"0.5", "1", "3", "3.5"},
        new String[]{"4", "5","5.5", "6"},
        new String[]{"6,5", "7", "8", "9", "9.5"},

        new String[]{"+0.5", "1", "2", "3", "3.5+", "3.5++"},
        new String[]{"4", "5","5.5+", "6"},
        new String[]{"+6,5","++6,5",  "7", "9", "9.5+", "9.5++"}
    );
  }

  public void test11() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"a", "a"},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"b"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test12() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{},
        new String[]{"7", "8", "9"},

        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void test13() throws FilesTooBigForDiffException {
    doTest(
        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"},

        new String[]{},
        new String[]{"4", "52", "6"},
        new String[]{}
    );
  }

  public void test14() throws FilesTooBigForDiffException {
    doTest(
        new String[]{},
        new String[]{"4", "52", "6"},
        new String[]{},

        new String[]{"1", "2", "3"},
        new String[]{"4", "5", "6"},
        new String[]{"7", "8", "9"}
    );
  }

  public void testGreediness() throws FilesTooBigForDiffException {
    doTest(
      new String[]{},
      new String[]{"x", "y", "z"},
      new String[]{},

      new String[]{"1"},
      new String[]{"2", "3"},
      new String[]{"4"}
    );

    doTest(
      new String[]{"1"},
      new String[]{"0"},
      new String[]{"3"},

      new String[]{"1", "4"},
      new String[]{"5", "0"},
      new String[]{"3"}
    );

    doTest(
      new String[]{"1", "2"},
      new String[]{"3", "5", "X", "Z", "7"},
      new String[]{"8", "9"},

      new String[]{"1", "2"},
      new String[]{"3", "4", "5", "6", "7"},
      new String[]{"8", "9"}
    );

    doTest(
      new String[]{"1"},
      new String[]{"5", "X", "Z"},
      new String[]{"9"},

      new String[]{"1", "2"},
      new String[]{"3", "4", "5", "6", "7"},
      new String[]{"8", "9"}
    );

    doTest(
      new String[]{"1"},
      new String[]{"5", "X", "7"},
      new String[]{"Z", "9"},

      new String[]{"1", "2"},
      new String[]{"3", "4", "5", "6", "7"},
      new String[]{"8", "9"}
    );
  }

  public void testEmptyPreviousText() throws FilesTooBigForDiffException {
    doTest(
      new String[]{},
      new String[]{},
      new String[]{},

      new String[]{},
      new String[]{},
      new String[]{}
    );

    doTest(
      new String[]{},
      new String[]{},
      new String[]{},

      new String[]{},
      new String[]{},
      new String[]{"x"}
    );

    doTest(
      new String[]{},
      new String[]{},
      new String[]{},

      new String[]{"x"},
      new String[]{},
      new String[]{}
    );

    doTest(
      new String[]{},
      new String[]{},
      new String[]{},

      new String[]{},
      new String[]{"x"},
      new String[]{}
    );

    doTest(
      new String[]{},
      new String[]{},
      new String[]{},

      new String[]{"z"},
      new String[]{"x"},
      new String[]{"y"}
    );
  }

  public void testEmptyCurrentRange() throws FilesTooBigForDiffException {
    doTest(
      new String[]{},
      new String[]{},
      new String[]{"x"},

      new String[]{},
      new String[]{},
      new String[]{}
    );

    doTest(
      new String[]{},
      new String[]{},
      new String[]{"x", "y", "z"},

      new String[]{},
      new String[]{},
      new String[]{}
    );

    doTest(
      new String[]{},
      new String[]{},
      new String[]{"x"},

      new String[]{},
      new String[]{},
      new String[]{"z"}
    );

    doTest(
      new String[]{},
      new String[]{},
      new String[]{"x"},

      new String[]{},
      new String[]{},
      new String[]{"y", "z"}
    );

    doTest(
      new String[]{"x"},
      new String[]{},
      new String[]{},

      new String[]{"y"},
      new String[]{},
      new String[]{"z"}
    );

    doTest(
      new String[]{"y"},
      new String[]{},
      new String[]{"w", "z"},

      new String[]{"y"},
      new String[]{},
      new String[]{"z"}
    );
  }

  public void testContent(){
    Block block = new Block("0\n1\n2\n3\n4\n5\n6", 3, 7);
    String blockContent = block.getBlockContent();
    assertEquals("3\n4\n5\n6", blockContent);

    block = new Block("0\n1\n2\n3\n4\n5\n6\n", 3, 7);
    blockContent = block.getBlockContent();
    assertEquals("3\n4\n5\n6", blockContent);

  }

  private static void doTest(
    String[] beforePrevBlock,
    String[] prevBlock,
    String[] afterPrevBlock,
    String[] beforeBlock,
    String[] block,
    String[] afterBlock) throws FilesTooBigForDiffException {

    String[] prevVersion = composeVersion(beforePrevBlock, prevBlock, afterPrevBlock);
    String[] currentVersion = composeVersion(beforeBlock, block, afterBlock);

    Block actualBlock = new Block(currentVersion, beforeBlock.length, beforeBlock.length + block.length).createPreviousBlock(prevVersion);
    Block expectedBlock = new Block(prevVersion,beforePrevBlock.length, beforePrevBlock.length + prevBlock.length);

    assertEquals(expectedBlock, actualBlock);

  }

  private static String[] composeVersion(String[] beforeBlock, String[] block, String[] afterBlock) {
    List<String> beforeList = new ArrayList<>();
    ContainerUtil.addAll(beforeList, beforeBlock);
    ContainerUtil.addAll(beforeList, block);
    ContainerUtil.addAll(beforeList, afterBlock);
    return ArrayUtil.toStringArray(beforeList);
  }
}
