package com.practice;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;
import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;

public class Abc {
    public static void main(String[] args){
        SparkSession spark = SparkSession.builder()
        .appName("Practice")
        .master("local[*]")
        .getOrCreate();

        Dataset<Row> df = spark.read()
        .option("header","true")
        .option("inferSchema","true")
        .csv("/data/input/employees.csv");  // read csv into df

        df.cache();

        df.show(); //display 20 records
        df.show(false);  // display 20 records without truncation of columns
        df.show(1,false); // display 1 record
        df.printSchema();  // prints schema of the file

        Dataset<Row> df1 = df.select(col("name"), col("salary"));

        df1.show(); // only displays two columns

        Dataset<Row> df2 = df1.filter(col("salary").gt(60000));

        df2.show(); // employees salary gt 60000

        Dataset<Row> df3 = df.filter(col("city").equalTo("Bangalore"));
        df3.show();

        Dataset<Row> df4 = df.withColumn("AnnualSalary",col("salary").multiply(12));
        df4.show();

        Dataset<Row> df5 = df.na().fill(0, new String[]{"salary"});
        df5.show();

        Dataset<Row> df6 = df.withColumn("salary",coalesce(col("salary"),lit(0)));
        df6.show();

        Dataset<Row> df7 = df6.filter(col("rating").equalTo(5));
        df7.show();

        df.count();

        Dataset<Row> df8 = df.filter(col("age").gt(28)); // gt, lt, geq, leq, equalTo, notEqual
        df8.show();

        Dataset<Row> df9 = df.sort(col("salary").desc()); // how to sort asc and desc
        df9.show();

        Dataset<Row> df10 = df.groupBy(col("dept_id"))
        .agg(count("*").alias("count"),
             sum("salary").alias("summ"),
             avg("salary").alias("avg"),
            max("salary").alias("max"),
         min("salary").alias("min"));

         df10.show();

         Dataset<Row> df11 = df.groupBy("city","rating").agg(count("*").alias("count"));
         df11.show();

         Dataset<Row> df12 = df.groupBy("dept_id").agg(sum("salary").alias("total"));
         df12.show();

         Dataset<Row> df13 = df.groupBy("dept_id").agg(count("*").alias("count")).filter(col("count").gt(2));
         df13.show();

         Dataset<Row> df14 = df.groupBy("city").agg(avg("salary").alias("avg")).filter(col("avg").gt(60000));
         df14.show();

         Dataset<Row> df15 = df.filter(col("rating").geq(4)).groupBy("dept_id").agg(count("*").alias("count"));
         df15.show();

         Dataset<Row> df0 = spark.read().option("header","true").option("inferSchema","true")
         .csv("data/input/departments.csv");

         Dataset<Row> df01 = df.alias("a").join(df0.alias("b"),df.col("a.dept_id").equalTo(df0.col("b.dept_id")),"inner").select(col("a.dept_id"),col("b.department"),col("a.salary"),col("a.name"));
         df01.show();

         Dataset<Row> df02= df.join(df0,df.col("dept_id").equalTo("dept_id"),"left").filter(col("dept_id").isNull());
         df02.show();

         Dataset<Row> custDf = spark.read().option("header","true").option("inferSchema","true").csv("/data/input/customers.csv");
         Dataset<Row> orderDf = spark.read().option("header","true").option("inferSchema","true").csv("/data/input/orders.csv");

         Dataset<Row> joinCO = custDf.alias("a").join(orderDf.alias("b"),"customer_id","inner")
         .select(col("a.name"), col("a.city"), col("b.order_id"), col("amount"), col("status"));
         joinCO.show();

         Dataset<Row> joinCO2 = custDf.alias("a").join(orderDf.alias("b"),"customer_id","left_anti");
         joinCO2.show();

         Dataset<Row> ord = orderDf.groupBy("customer_id").agg(sum("amount").alias("sum")).orderBy(col("sum").desc()).limit(1);
         ord.show();

         Dataset<Row> ord1 = orderDf.filter(col("status").equalTo("COMPLETED")).groupBy("product_category").agg(sum("amount").alias("total_revenue"));
         ord1.show();   

         Dataset<Row> ord2 = orderDf.withColumn("date_conv",to_date(col("order_date"),"yyyy-MM-dd")).withColumn("year_month",date_format(col("date_conv"),"yyyy-MM"));
        Dataset<Row> ord3 = ord2.groupBy("year_month").agg(sum("amount").alias("sum"));
         ord3.show();
        
        Dataset<Row> cancPercentage = orderDf.agg(count("*").alias("total_records"),
    count(when(col("status").equalTo("CANCELLED"),true)).alias("cancelled_records")
    ).withColumn("percentage",col("cancelled_records").divide(col("total_records"))
    .multiply(100));
    cancPercentage.show();

    Dataset<Row> empDf1 = spark.read().option("header","true").option("inferSchema","true").csv("data/input/employees.csv"); 
    WindowSpec window = Window.partitionBy(col("dept_id"))
    .orderBy(col("salary").desc());

    Dataset<Row> empperdept = empDf1.withColumn("rank",
        dense_rank().over(window)
    ).filter(col("rank").equalTo(1)).select(col("name"),col("dept_id"), col("salary"));

empperdept.show();

      StructType customerSchema = new StructType()
        .add("customer_id", DataTypes.IntegerType)
        .add("name", DataTypes.StringType)
        .add("city", DataTypes.StringType);

StructType itemSchema = new StructType()
        .add("product_id", DataTypes.StringType)
        .add("qty", DataTypes.IntegerType)
        .add("price", DataTypes.DoubleType);

StructType orderSchema = new StructType()
        .add("order_id", DataTypes.IntegerType)
        .add("customer", customerSchema)
        .add("items", DataTypes.createArrayType(itemSchema));

    }
}
