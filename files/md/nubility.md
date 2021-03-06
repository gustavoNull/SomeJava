### idea 社区版创建web工程，并部署tomcat
        1. 创建webapp工程
        2. Edit Configurations->add maven   Command Line: tomcat7:run
        3. 先在pom.xml中加入tomcat7的插件
        ```
                <build>
                    <finalName>TestArtif</finalName>
                    <plugins>
                      <plugin>
                        <groupId>org.apache.tomcat.maven</groupId>
                        <artifactId>tomcat7-maven-plugin</artifactId>
                        <version>2.1</version>
                        <configuration>
                          <port>9090</port>
                          <path>/</path>
                          <uriEncoding>UTF-8</uriEncoding>
                          <server>tomcat7</server>
                        </configuration>
                      </plugin>
                    </plugins>
                 </build>
        ```	 
        引用自： https://blog.csdn.net/u012364631/article/details/47682011
        
### ArrayList的elementData为什么要修饰为transient?
        ArrayList的实现原理是动态数组，elementData是个存储数据的数组，随着不断的往数组add数据，如果数组容量不够，要进行扩容，ArrayList扩容是在  
    当前数组容量的1.5倍扩容，ArrayList里真正存储数据的个数是size，size肯定是小于elementData数组的容量的，elementData用transient修饰的目  
    的是，序列化的过程中不序列化elementData，而是通过实现readObject（）和writeObject（）两个方法来实现序列化，问：readObject（）和  
    writeObject（）是private的，是怎么调用的？ 肯定时反射，文章：https://www.cnblogs.com/sharkli/p/5607895.html

    
### 注意点： 导入hamcrest包后执行单元测试，有时会出现错误： java.lang.NoSuchMethodError: org.hamcrest.core.AllOf.allOf(Lorg/hamcrest/Matcher;Lorg/hamcrest/Matcher;)Lorg/hamcrest/Matcher;
      
导致该错误的原因是jar引入顺序导致，因为如果hamcrest的jar包放到了junit jar包的后面则程序会默认调用junit jar包里面的 hamcrest core，就会出现上面的问题了
        
### Redis 查看所有的key的命令： keys *  
### git相关
 - git clean -xfd 清除所有
 - git branch -a 查看远程分支
 - git branch 查看本地分支
 - git branch branchName 创建本地分支
 - git branch -d branchName 删除本地分支
 - git checkout branchName 切换到新分支(该branchName 是git branch命令 查看显示的分支，是本地分支)
 - git checkout -b branchName 相当于 git branch branchName + git checkout branchName
-  git log fileName  查看某文件的变更记录
        
- 从远程分支拉到本地分支
        git checkout -b localName remoteBranchName  
    工作中发现，如果本地分支名称和远程分支名称不一致导致提交失败，所以最好让本地分支和远程分支名称一致，  
    也就是如果远程分支是  origin/release/V1.2.3.4, 本地分支名要是release/V1.2.3.4  
        git branch -vv 查看本地分支和远程分支的对应关系  
        git tag 可以查看标签  
        查看完标签可以用git reset --hard tag 切换到标签  
        git add . 增加文件，删除文件时 git add -A .  
        git push origin master 将本地的master分支推送到origin主机的master分支  
        git reset 提交号 回到某次提交（提交号通过git log查看）  
        git reset --hard 提交号 文件恢复到某次提交  
        这时如果想要回到最新状态 git reset --hard origin/master           
                               git reset --hard origin/V12.17.10  
                               
        unlink of file .. failed should I try ... 出现此告警提示，执行如下操作：
        git gc --auto
        git repack -d -l
        
        查看某个文件的提交记录
        git log 文件名
        git log --pretty=oneline 文件名
        
        删除远程分支 git branch -r -d origin/branchname
        git push origin :branchName
    
    git提交提示错误：can not push in mirror
    原因解析git 没有配置pushInsteadOf , 可以通过命令配置，也可以通过修改.gitconfig
    .gitconfig 位置 window ： c:\\users\\user 下
            linux : /root/ 下
-	git检出与提交如果涉及换行符的转换就配置core.autocrlf 为true，如果不需要就配置为false	
-	git如果删除了文件可以用git reset HEAD来恢复
        
### spring cloud
        每个使用配置管理的客户端项目中一个名为bookstrap.yml的本地配置文件，用来设定连接配置管理服务器、应用的名称、以及需要有配置管理服务器提供的配置文件等参数
        例如：
        spring:
          application:
            name:web
          profiles:
            active:development
          cloud:
            config:
              uri:http://localhost:8888
                        
     以上配置是一个配置管理客户端的bookstrap.yml的配置信息，它包含的信息是：该客户端使用的配置文件是http://localhost:8888配置服务器的web-development.yml配置文件，如果profiles.active不配置，则对应web.yml配置文件
     
     @RefreshScope是解决配置信息在线实时更新的，可以用在服务端和客户端，但是必须要有spring-boot-starter-actuator 这个依赖才可以

### docker
        docker build -t demo:v1 . 制作镜像
        
        
### Dockerfile
        FROM 基础镜像,必须为第一条指令
        VOLUME 可以实现挂载功能
        语法为： VOLUME ["/data"]
        RUN 运行指定的命令，例如：
                RUN /bin/bash -c 'echo hello'
                RUN ["/bin/bash", "-c", "echo hello"]
        ADD 复制命令，把文件复制到镜像中， 如果把虚拟机与容器想象成两台linux服务器的话，那么这个命令就类似于scp
        ENTRYPOINT 功能是启动是的默认命令
        语法如下：

        1. ENTRYPOINT ["executable", "param1", "param2"]
        2. ENTRYPOINT command param1 param2

### hashMap和HashTable 区别
### 任务
        java实现死锁
        java锁的理解， synchronize，Lock 区别和优缺点
        pubilc synchronized void test() {}
 
 
        pubilc synchronized void test() {}   //synchronized用在方法上
        
        synchronized也可以用在代码块上：
        public void test() {
             synchronized(obj) {
                  System.out.println("===");
             }
        }
        synchronized 用在方法和代码块上有什么区别呢？
### Java 基础
- 有点类构造方法里会有super(),目的是引用父类的无参构造器，这个super()是可以省略的
- 在idea里执行 Thread.activeCount() 为什么为2
- Integer和AtomicInteger区别
- 优先使用ConcurrentHashMap,而不是Collections.SynchronizedMap或Hashtable
- Runnable与Callable的区别
- 引用类的final static常量，不会初始化该类，引用static变量，会初始化该类
    例子：

```java
        public class InitialTest {
            public static void main(String[] args) {
                System.out.println(Class1.a);
                System.out.println(Class2.a);
            }
                class Class1 {
            final static String a = "hello";

            static {
                System.out.println("Class1 init");
            }
        }

        class Class2 {
            static String a = "hello";

            static {
                System.out.println("Class2 init");
            }
        }
        }
```
    输出为：
    hello
    Class2 init
    hello
    
- BlockingQueue 了解一下
- Thread的interrupt()不会中断正则执行的线程，只会中断sleep、wait、join的阻塞状态的线程
- 查看程序InterruptTest和InterruptTest1的区别，只在InterruptTest1里面中断了阻塞的状态，抛出异常：java.lang.InterruptedException: sleep interrupted，通过interrupt()中断阻塞的线程，只是将线程的中断标志位置1，该中断标志位可通过isInterrupted()获得。
- ReentrantLock常用的有三个锁的方法，lock(), tryLock(),lockInterruptibly（）,弄清楚
- LockTest大概演示了lock（）的用法，当一个线程想要获取一个锁的时候，如果这个锁被其他线程占有，这个线程就处于阻塞的状态，此时如果执行该线程的interrupt()方法，会把该线程的中断标志位置1，并不会立刻终止该线程，LockTest的输出为：
```
    Thread-0 in run()...
    Thread-0 out run()...
    Thread-1 in run()...
    Thread-1 interrupt()...
```
说明t2线程执行interrupt（）之后，还是继续等待并获取了锁，只是在执行阻塞方法sleep时，由于该线程的中断标志位为1，所以直接抛出异常
    
- LockInterruptTest 演示了lockInterruptibly()方法，当一个线程想要获取一个锁的时候，如果这个锁被其他线程占有，这个线程就处于阻塞的状态，此时如果执行该线程的interrupt()方法，立刻抛出异常，因为lockInterruptibly()方法抛出InterruptedException
    
- JdbcTemplate 了解一下：https://www.cnblogs.com/tuhooo/p/6491913.html
- 平衡二叉树和红黑树熟悉一下  
- 与null比较，比如a==null,通常用null==a 的方式来表示，优点是外一把"=="不小心写成： "="，程序会报错，否则这个错误很难定位。  
- 双重检查单例模式
- 不要在finally中赋值，更不要在finally中return
- 新项目中日志最好使用slf4j+logback的组合日志
- ？？？为什么TCP是长连接的，而基于TCP的HTTP协议是无连接的？？？
- Integer.toHexString()可以实现十进制到十六进制的转换，
- Integer.toBinaryString 方法是把十进制转换成二进制，测试35和-35的二进制分别为：100011\11111111111111111111111111011101，二进制是用补码的方式显示，正数的补码和原码一样，负数的补码是取反加一
- 移位运算符<<和>> 是针对整形和长整形的，如果对整形运算，移位的位数是要对32取余数，例如35>>33等于35>>1(35是整形数)，如果long l = 35; l>>65==l>>1
- java 不支持向下转型，精度变低，例如 int i=1; long l =i; 可以，但是long l=2; int i=l; 确不可以
- 默认小数都是double类型，如果小数要赋值给float要加f，例如float f = 1.2f; 如果float f=1.2 就会报错
- 字面量常量是
- Class.forName和ClassLoader.loadClass区别
- 小数默认是double类型，如果小数赋值给float要加f，例如float f=1.2f;

### 泛型
- 泛型的栗子：GenericDemo， 方法的泛型是放在<>里面，尖括号里的是代表一种未知类型，可以随便写比如<shui,bian,xie>,泛型一定要放在方法法返回值前
- 下的的情况是赋值失败的：
```java
        List<Integer> list1 = new ArrayList<>();
        List<Object> objectList = list1;
```
- List\<?\>是通配符，不能add，可以clear和remove，可以把任何类型的集合赋给List\<?\>
- 《码出高效》中的栗子，Garfield继承自Cat，Cat继承于Animal, List\<Garfield\>不能赋值给List\<? super Cat\>, List\<Animal\>不能赋给List\<? extends Cat\>, 任何元素都不能添加到List\<? extends T\> 集合内，List<\? super Cat\>集合只能添加Cat或Cat的子类， List<\? super T\>可以get，但是只能返回Object类型，List\<? extends Cat\>可以get，只能返回Cat或Cat的父类


### 反射了解一下
- RTTI，编译器在编译时打开和检查.class文件
- 反射，运行时打开和检查.class文件   		
### 事务了解一下

### IDEA 记录
#### IDEA 编译中文乱码的问题
- 	bin文件夹下面idea64.exe.vmoptions和idea.exe.vmoptions这两个文件，分别在这两个文件中添加：-Dfile.encoding=UTF-8  
    找到intellij idea的file---settings---Editor---FileEncodings的GlobalEncoding和ProjectEncoding和  
    Default encoding for properties都配置成UTF-8  
-	IntelliJ IDEA中pom.xml报错project上Failed to read artifact descriptor for XXX.jar: 通过everything工具搜索lastupdate,把所有的搜索到记录都删除，然后在重新刷新maven导入
#### idea有时引入多工程时，跑UT路径问题
- 	在project Structure里，Modules里能看到多个工程，把Paths标签的“Output path”和"Test output path"修改成对于的路径就可以
#### IDEA 快捷键
-	shift+F8是跳出当前方法，如果想要继续运行到下个断点，可以用快捷键F9， 也页面的位置是Debug窗口，和"View Breakpoints"按同一排的"Resume Program"

### 例子WriteInfo中，向excel写数据，有时会出现java.lang.OutOfMemoryError: Java heap space 异常，不同的机器出现异常几率不同
    解决方案：1. IDEA通过-Xms512M -Xmx800M，增加jvm内存值，-Xms表示最小内存， -Xmx表示最大内存
    
###
```java
    byte b = -1;
    System.out.println(b);
    System.out.println(b & 0xff);
```
	输出为：-1,255
	原因是System.out.println的输入如果是byte类型，是要转换成int，-1的byte表示为1111_1111, -1的int表示
	为1111_1111_1111_1111_1111_1111_1111_1111, b & 0xff 相当于只截取低8位的值 
-   java中整数变量有byte、short、int、long，把整数赋值给整数变量时，如果能装下就赋值成功，否则就报错，编译失败，比如1可以赋值给
byte、short、int、long，但是有一点，如果把一个超过int的范围的值赋值给long时，要在字面量值加L（不要加小写l，因为容易和1弄混），
一个字面量值通常是int型的，
-   short s =1; s=s+1; 是编译失败的，因为s+1的s是short型，1是整形，首先要将s向上转型为int，然后执行加1，然后赋值给s，将int型赋值给short型。
则赋值失败，可以用s=(short)(s+1)或s+=1来实现
-   


### 关于double的精度
```
	double d = 19.99;
    double d1 = d*10;
	double d2 = 19.989999999999998, 
```
	d=19.99,但是d1=199.89999999999998,（可以试着打印输出看看）这是为什么呢？  
	因为不管是double（64位）还是float（32位），都是由固定的几位来存储小数，各个位的精度为1/2、1/4、1/8、1/16..., 当把19.99赋  
	值给d时，  一个double类型就不可能完全存储19.99的值，必然有一些精度的误差，但不是所有的double类型都不能完全存储，比如19.5就  
	可以完全存储在double  	类型中，  d和d2在内存中存储的是一样的，可以通过MyUtils.double2Bytes将double转成byte数组来查看 
	也可通过Double.doubleToRawLongBits来查看两个double类型转成long类型的值  
	如果对小数精度有要求，最好用BigDecimal，BigDecimal有3个构造方法，最好不要用double的构造方法，可以用BigDecimal.valueOf或  
	String的构造方法。下面里例子，输出值b1为1999.0，b3为1998.9999999999998
	
```
    BigDecimal b1 = new BigDecimal("19.99").multiply(new BigDecimal("100"));
    System.out.println(b1);
    BigDecimal b3 = new BigDecimal(19.99).multiply(new BigDecimal(100));
    System.out.println(b3.doubleValue());
```
	BigDecimal的除法如果不能整除会抛异常，使用时必须先指定小数点精度  
	MyUtils.double2Bytes方法里面先通过Double.doubleToRawLongBits把double转成long，然后在把long的8个字节存入到byte数组中  
	q因为double和long都是64为的、8个字节，占用的空间是一样的，也可以通过Double.longBitsToDouble把long型转成double型数据  
        
### 需要强化
	1. 多线程基础，多线程的应用
	2. jvm基础
	3. mysql
	4. spring

### java日志学习

### idea 快捷键
	for循环快捷键：
	https://blog.csdn.net/mingjie1212/article/details/51143444  
	查看源码时如果想看类的继承关系，可以在IDEA类上右键Diagrams
	
### github 
	换行的方法：两个空格加一个回车，直接回车不能换行  
	两个'~'直接的词根java中过期的方法显示一样，是在中间加横岗，例如： ~~你好~~  
	https://try.github.io/ 是github交互式学习教程  
	https://github.com/pcottle/learnGitBranching git学习教程 
	git 多学习下，至少常用的命令要熟练使用  
	一些符号要通过反斜杠来进行转移，
	\   反斜线    
\`   反引号  
\*   星号  
\_   底线  
\{\}  花括号  
\[\]  方括号  
\(\)  括弧  
\#   井字号  
\+   加号  
\-   减号  
\.   英文句点  
\!   惊叹号  
\<\> 尖括号  

	
### Gradle
	gradle下载慢的问题，用阿里的国内云仓库：
	repositories {
        //mavenCentral()
        maven {
            url 'http://maven.aliyun.com/nexus/content/groups/public/'
        }
    }
    
### @SuppressWarnings的作用
	@SuppressWarnings的作用是让编辑器对代码的一些警告保持沉默，例如代码中引用的过期的（@deprecation）方法，在ide中就会在该方法上加个横杠  
	以表示该方法已过期，但是如果不想在IDE里出现这样的提示就可以用@SuppressWarnings（"deprecation"）来解决， 还有类型转换时IDE也会提示，  
	如果想要忽略提示，就可以用@SuppressWarnings（"unchecked"）
	
### junit jmockit
-	测试时发现个问题没有解决，如果想要mock一个静态方法，而该方法没有返回值（void）,网上找了一些方法都是有result 来返回的，但是不返回的方法还没找到  
-	测试发现，不返回的static方法不用加result 只有一个方法mock就可以  
-	有个例子可以继续研究DBUtils里有个静态getConn方法，StaticClass方法有个静态方法getSqlResult，引用了该方法  
-	有个jmockit的的使用例子：http://jmockit.cn/showArticle.htm?channel=2&id=4　　
-	用jmockit来mock对象时，一般用@Mocked注解来完成，但是有些类里面有一些静态的方法，和静态块，到时mock类失败，提示NoClassDefFoundError,遇到这种问题，可以通过@Mocked(stubOutClassInitialization = true) 来完成mock，
-	通过Mocked可以mock对象，但是如果想要获取多个实例，每个实例的mock值不同，该怎么办？？

### this.getClass().getClassLoader().getResource("test/resources/file.txt").getPath(); 
	写ut是如果需要文件，可以用这种方式获取文件路径
	发现在本地跑ut和项目上跑的ut如果涉及resources路径，就会不一致，暂时可以通过以下方式获得resources的路径：
```
    public static String getResourceRootPath() {
        return isRunningInIDE() ? getIDEResourcePath() : getBuildResourcePath();
    }

    private static String getIDEResourcePath() {
        return (Class.class.getClass().getResource("/").getPath() + File.separator).substring(1).replace("%20", " ");
    }

    private static String getBuildResourcePath() {
        return Class.class.getClass().getResource("/").getPath().replace("classes", "resources") + File.separator;
    }

    public static boolean isRunningInIDE() {
        String runTimeRootPath = Class.class.getClass().getResource("/").getPath().substring(1).replace("%20", " ").replace("/", File.separator);
        return runTimeRootPath.contains(File.separator + "out" + File.separator);
    }
```  
  

	
### java File
    最近的工作涉及文件相关的一些操作，记录下来
```
        for (String arg : args) {
            System.out.println("string: " + arg);
            File file = new File(arg);
            System.out.println("getAbsolutePath: " + file.getAbsolutePath());
            System.out.println("isDirectory: " + file.isDirectory());
            System.out.println("isAbsolute: " + file.isAbsolute());
            System.out.println("isFile: " + file.isFile());
            System.out.println("exist: " + file.exists());
        }
``` 
    如果输入为一个存在的目录输出为：
```
String: C:\Users\gustaov\Desktop\tmp
getAbsolutePath: C:\Users\gustaov\Desktop\tmp
isDirectory: true
isAbsolute: true
isFile: false
exist: true
```
    如果输入为一个不存在的目录：
```
String: C:\Users\gustaov\Desktop\tmp1
getAbsolutePath: C:\Users\gustaov\Desktop\tmp1
isDirectory: false
isAbsolute: true
isFile: false
exist: false
```    
    如果输入一个文件：
```
String: C:\Users\gustaov\Desktop\tmp\test.class
getAbsolutePath: C:\Users\gustaov\Desktop\tmp\test.class
isDirectory: false
isAbsolute: true
isFile: true
exist: true
```    
    如果只输入一个文件的相对路径：
```
String: test.class
getAbsolutePath: C:\Users\gustaov\Desktop\tmp\test.class
isDirectory: false
isAbsolute: false
isFile: true
exist: true
```    
    如果输入目录的相对路径
```
String: src
getAbsolutePath: C:\Users\gustaov\Desktop\tmp\src
isDirectory: true
isAbsolute: false
isFile: false
exist: true
```    

    只有文件存在（exist为true），才可能是文件或是目录，否则isDirectory和isFile肯定都是false
    
    
### python
	python -m SimpleHTTPServer 8000提供一个文件浏览的web服务，可以通过http访问当前目录
	
### Exception
	RuntimeException 运行时异常，编译器不会检查RuntimeException异常
	编译器不会检查RuntimeException异常。例如，除数为零时，抛出ArithmeticException异常。RuntimeException是ArithmeticException的超类。  
	当代码发生除数为零的情况时，倘若既"没有通过throws声明抛出ArithmeticException异常"，也"没有通过try...catch...处理该异常"，也能通过编译。 
	这就是我们所说的"编译器不会检查RuntimeException异常"！数组越界时产生的IndexOutOfBoundsException异常，fail-fail机制产生的  
	ConcurrentModificationException异常等，都属于运行时异常。  
	Exception类本身，以及Exception的子类中除了"运行时异常"之外的其它子类都属于被检查异常
	
### jsoup 解析html
	把test.html的路径解析

### good tools
- 正则表达式的工具：regex match traceer	


### 正则
-  怎么在匹配是利用前面匹配到的分组，例如<123>123，可以用<(.*)>匹配出123，然后后面的123怎么能利用前面已经匹配到的123？  
- [正则表达式中的的向前匹配、向后匹配、负向前匹配、负向后匹配](https://blog.csdn.net/newborn2012/article/details/18262677)
	
### 网络
	创建新的Socket客户端对象之后，就完成了三次握手，java底层封装了这个连接过程，开发者不需要考虑

### 常见问题
-	Module xxx is imported from Maven.Any changes made in its configuration may be lost after reimporting, 
解决办法：pom.xml 增加配置：
```
    <build>  
       <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

### Writer 相关
-	修改kw中发现的问题，FileWriter fw = new FileWriter(); BufferedWriter bw = new BufferedWriter(fw);报错FileWriter fw = new FileWriter();没有指定编码方式，通过查看FilelWriter 源码发现，FileWriter并没有指定编码方式，而是采用系统默认的编码，如果想要手动指定编码方式，应该用Writer w = new OutputStreamWriter(new FileOutoutStream(file), "GBK"); 在代码中最好都要指定编码，不要依赖于系统的编码

### Nginx
-   windows 的host文件地址：c:\windows\system32\drivers\etc， 生效的命令：ipconfig /flushdns，刷新成功够提示：已成功刷新 DNS 解析缓存。
可以把hosts中增加127.0.0.1 www.gustavo.com， 然后ipconfig /flushdns刷新DNS， 然后所有www.gustavo.com的访问都会转向127.0.0.1
比如，之前localhost:8080/hello的访问，可以使用www.gustavo.com:8080/hello的方式来访问
- 常用命令：
关闭nginx：nginx -s stop 
判断配置文件是否正确： nginx -t
重启加载配置文件： nginx -s reload
- 通过nginx访问静态资源， 配置如下：
```
    server {
        listen       8855;
        server_name  test1.com localhost;

        #charset koi8-r;

        access_log  logs/host.access.log  main;

        location /math {
            root   D:/workspace/pdfd/books-master/;
            autoindex  on ;
        }
    }
``` 
然后访问：localhost：8855/math，就可以访问D:/workspace/pdfd/books-master/math的静态资源，如下配置也可以：
```
     location / {
            root   D:/workspace/pdfd/books-master/math
            autoindex  on ;
        }
```
只是访问时，通过localhost：8855，就可以
-	通过nginx做代理, 可以在配置文件中增加：
```
location /hello {
		proxy_pass	http://localhost:9090/hello2;
        }
```
http://localhost:9090/hello2 是可以直接访问的，现在通过nginx代理来访问，而不是直接访问， 通过localhost：8854/hello，得到了和直接访问http://localhost:9090/hello2 一样的结果，可以得知，nginx完成了代理服务的任务
-	关于nginx作为代理服务器，有关location的配置的一些记录，现在有服务和返回的列表如下：
localhost:9090/proxy/  :   proxy
localhost:9090/proxy/a  :   proxy.a
localhost:9090/proxy/b  :   proxy.b
localhost:9090/a        :   a
localhost:9090/b        :   b
1. 如果配置如下：
```
	location /proxy/ {
		proxy_pass http://127.0.0.1:9090;
	}
```
访问localhost:8854/proxy/a,则返回proxy.a,说明被代理到了localhost：9090/proxy/a
2. 如果配置文件如下：注意：跟上一个配置的区别是多了一个/
```
	location /proxy/ {
		proxy_pass http://127.0.0.1:9090/;
	}
```
访问localhost:8854/proxy/a,则返回a,说明被代理到了localhost：9090/a
-	location匹配规则： https://blog.csdn.net/zwl18210851801/article/details/81699977
- 	关于静态资源访问的： https://blog.csdn.net/zzq900503/article/details/72821081
-   	最近想熟悉前后端分离网站的部署流程，后端springboot，前端用vue,用nginx做代理，这几天nginx大概了解怎么用了，花几天时间学学vue
- 	a简单能够把vue项目通过npm run build打包后，用nginx来运行访问，nginx的配置信息，在这个连接：https://my.oschina.net/u/1760791/blog/1575808

### dump文件分析
-	Memory Analyzer分析工具下载地址：https://www.eclipse.org/mat/downloads.php
- 	a测试步骤：1. top命令查看pid  
	2. jmap -dump:live,format=b,file=filename.hprof pid
	3. MemoryAnalyzer分析工具导入生成的dump文件
Retained Heap的值是字节数，转换成M需要把该值除1024*1024


### 技术
- 	putty遇到ctrl+s，Ctrl+q就可以解决
-	maven工程打包后java -jar xxx.jar来运行，提示：没有主清单属性，可以通过maven的pom.xml文件增加下面内容来解决：
```
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>1.2.1</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>timer.ScheduleManager</mainClass>
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### Telnet 服务器
-   TelnetServer
