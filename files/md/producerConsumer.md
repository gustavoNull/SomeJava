## 生产者消费者模式一个有三种实现方式

### 用阻塞队列实现生产者消费者模式
  见 ProducerConsumer.java, 该例子中，用ArrayBlockingQueue实现，这个队列最大能存储5个元素，一个生产者，一个消费者

### 用wait() notify()实现生产者消费者模式
  执行wait()\ notify()之前，首先要获得对象的锁，锁的对象和执行wait的对象应该是一个对象，而不应该锁一个对象，而去执行另一个对象的wait notify
  例如：下面的例子：Resource中锁的是list，然后对Resource对象执行wait是不对的，Resource1 和 Resource2是正确的
```
  public class Resource {
    LinkedList<String> list = new LinkedList<>();

    public void addProduce() {
        synchronized (list) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
  
public class Resource1 {
    LinkedList<String> list = new LinkedList<>();

    public void addProduce() {
        synchronized (list) {
            try {
                list.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Resource2 {
    LinkedList<String> list = new LinkedList<>();

    public synchronized void addProduce() {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

```
- 例子WaitNotify.java 就是使用wait、notify实现生产者消费者模式的例子
- ProducerConsumer_waitNotify_notgood 也是wait、notify实现生产者消费者的例子，但是有缺陷，需要优化
- ProducerConsumer_waitNotify_good 是完善的代码,它在notgood上的只优化了一个地方：不在锁里sleep（sleep的目的是不让程序跑的太快，不加其实也可以）
  另外一个注意点：每个线程里的wait一定要用while包围，因为多个生产者和多个消费者都是使用同一把锁，任何一个生产者notifyAll的时候可能唤醒的也是生产者
  因此条件需要再次判断
- ProducerConsumer_Condition 是用Condition的await、signalAll 来实现的生产者消费者模式，跟wait、notify一样，判断条件依然要再次判断

### 使用Condition的await、signal来实现生产者消费者模式
  ConditionDemo例子就是用Condition实现的生产者消费者模式的例子，用Condition比用wait、notify好的原因是，一个锁可以创建多个Condition，  
  可以更灵活的控制哪些条件的线程取执行，而wait、notify是，notifyAll方法把所有的wait的线程都唤醒
