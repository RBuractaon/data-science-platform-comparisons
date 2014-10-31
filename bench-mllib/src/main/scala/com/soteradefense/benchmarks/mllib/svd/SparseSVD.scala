package com.soteradefense.benchmarks.mllib.svd

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.mllib.linalg.distributed.{CoordinateMatrix, MatrixEntry}


object SparseSVD {


  def main(args: Array[String]) {
    var myrcond = 1e-9
    if (args.length == 5) {
      myrcond = args(4).toDouble
    } else if ((args.length > 5) || (args.length < 4)) {
      //System.err.println("Usage: SparseSVD <matrix file> <parallelism> <results stub> <compute U bool> <rCond> <coalesce k> <shuffle>")
      System.err.println("Usage: SparseSVD <matrix file> <parallelism> <results stub> <nsv> <rCond>")
      System.exit(1)
    }

    val filename = args(0).split("/")
    val conf = new SparkConf().setAppName("SparseSVD: " + filename(filename.length-1))
    val sc = new SparkContext(conf)

    // Load and parse the data file.
    val matrixEntries = sc.textFile(args(0), args(1).toInt).map { line =>
      val tokens = line.split(' ').map(_.trim())
      MatrixEntry(tokens(0).toLong, tokens(1).toLong, tokens(2).toDouble)
    }
    //val cnt = matrixEntries.count()

    val coordmat = new CoordinateMatrix(matrixEntries)        // Original Statement without .coalesce()
    //val coordmat = new CoordinateMatrix(matrixEntries.coalesce(     // Match partitions from above
    // args(1).toInt-1,shuffle = true))
    //val coordmat = new CoordinateMatrix(matrixEntries.coalesce(       // Partition is number of columns
    //  args(5).toInt-1, shuffle = args(6).toBoolean))
    //val coordmat = new CoordinateMatrix(matrixEntries.coalesce(     // Scale Partitions with the total non-zero values
    //  Math.max(args(1).toInt,cnt.toInt/100),shuffle = true))

    // Compute SVD
    val mat = coordmat.toIndexedRowMatrix()
    //val svd = mat.computeSVD(mat.numCols().toInt, computeU = args(3).toBoolean, rCond = args(4).toDouble)
    var nsv = Math.min(args(3).toDouble,mat.numCols().toDouble)
    val svd = mat.computeSVD(nsv.toInt, computeU = true, rCond = myrcond)
    //val U: RowMatrix = svd.U
    //val s: Vector = svd.s
    //val V: Matrix = svd.V

    //sc.parallelize(svd.s.toArray.toSeq).coalesce(1,shuffle = true).saveAsTextFile(args(2) + "_singular_values")
    //sc.parallelize(svd.s.toArray.toSeq).saveAsTextFile(args(2) + "_singular_values")
    println("Number of Singular Values Computed: " + svd.s.size)
    println("Max Singular Value: " + svd.s.toArray.max)
    //println("Singular Values: " + svd.s.toString().replaceAll(",","\n"))
    sc.stop()
  }
}