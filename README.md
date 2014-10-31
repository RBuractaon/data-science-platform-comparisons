data-science-platform-comparisons
=================================

Benchmarking of the latest data science platforms.

The goal of this project is to compare the runtime of common analytic tasks using many of the popular data science platform/frameworks available. 


Data Science Platforms
----------------------
The following list are the platforms/frameworks that have been chosen for testing. Since the cloud used for testing uses Cloudera a priority were given to those frameworks that are apart of Hadoop or are able to integrate with YARN, and read/write to HDFS. Another criteria was that the version of the product be the open source version. The reasoning being that this would allow easier collaboration/sharing of analytics between Data Scientists between companies/organizations/institutions and between clouds/clusters. All platforms claim blazing fast performance so let's get the tires on the pavement and take them all for a spin.

###### Spark (Apache)
Fast and general engine for large-scale data processing (https://spark.apache.org/)
-Release Used: Spark 1.1.0 (installation was handled via Cloudera CDH 5.2)
-API Language: Scala, Python, Java
-HDFS Read/Write: Yes
-YARN Integration: Yes
Specific Libraries Tested
- MLlib (Machine Learning Library)
- GraphX (Graphs and Graph-Parallel Computation)

###### Apache Giraph
Iterative graph processing system built for high scalability (http://giraph.apache.org/)
Open-source counterpart to Pregel and uses Bulk Synchronous Parallel (BSP) model of distributed computation.
-Release Used: Giraph 1.1.0-SNAPSHOT built for CDH 5.0.0
-API Language: JAVA
-HDFS Read/Write: Yes
-YARN Integration: Yes

###### GraphLab Create
A Machine Learning platform for data scientists and business analysts with some programming background to easily take their ideas from inspiration to production (http://graphlab.com). Includes many analytics that were not easily implemented in PowerGraph (described below). Unfortunately there are some algorithms in PowerGraph that have yet to be ported over to Create.
-Release Used: 1.0.1
-API Language: Python

###### GraphLab PowerGraph
Open Source version of GraphLab project that started at Carnegie Mellon University in 2009 (http://graphlab.org).
The goal of the project was to create a new parallel computation abstraction tailored to machine learning.
-Release Used: 2.2
-API Language: C++
-HDFS Read/Write: Yes
-YARN Integration: ### NO

###### SciDB
A computational DBMS for data-obsessed organizations (http://www.scidb.org). Complete data base management system that is array based (as opposed to row oriented, or columnar store based systems). Graph algorithms are implemented using linear algebra methods since these matrix operations are easily performed directly to the data at rest. The database operations natively uses the Array Functional Language, AFL. These queries are parsed and translated to compiled C/C++ operations and brodcast through the framework. The Array Query Language, AQL, is a SQL like language that allows for easy transition into this platform. Behind the scenes all AQL queries are converted/translated to AFL prior to execution. The same goes for the Python and R interfaces which are translated to AFL. 
-Release Used: 14.8 (Community Edition / Open Source)
-API Language: AFL, AQL, Python (through SciDB-Py: https://github.com/Paradigm4/SciDB-Py), R (through SciDB-R: https://github.com/Paradigm4/SciDBR)
-HDFS Read/Write: ### NO
-YARN Integration: ### NO

Baseline Performance (Single Node)
----------------------------------
Comparison was also done against the available scientific computing libraries/modules (such as Python and R). 
Prior to all of these platforms, the basic work-flow involved downloading a manageable subset/sample of the data to train a model. Also, sometimes it is much easier to just write an algorithm single-threaded and just use Map/Reduce streaming to parallelize the computation. Also many statisticians, mathematicians, engineers, heavily rely upon the algorithms available via an R library, Python module, or Matlab Toolbox.  
Scientific Computing Languages
-Python: NumPy and SciPy
-R


Hardware Used for Testing
-------------------------
###### Silverdale Computing Cluster/Cloud
The computing cluster being used for performance comparison is configured as follows:



