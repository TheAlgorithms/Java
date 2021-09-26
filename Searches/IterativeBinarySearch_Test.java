package Searches;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IterativeBinarySearch_Test {

  //	kiem thu luong du lieu
  //	ham search dung de tim key trong mang da xap xep
  //	dua theo tieu chi all-du path tim dc 4 tescase
  Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  IterativeBinarySearch search = new IterativeBinarySearch();

  @Test
  void tc1_key_khong_co_trong_mang() {
    assertEquals(-1, search.find(arr, 11));
  }

  @Test
  void tc2_key_nam_chinh_giua_mang() {
    assertEquals(5, search.find(arr, 5));
  }

  @Test
  void tc3_key_nam_cuoi_mang() {
    assertEquals(10, search.find(arr, 10));
  }

  @Test
  void tc4_key_nam_dau_mang() {
    assertEquals(0, search.find(arr, 0));
  }
}
