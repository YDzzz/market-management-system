package Service;
import java.util.NoSuchElementException;
/**
 * 自定义实现循环队列
 */
    public class MyQueue<E> {
        // 队列头部，指向队列头的第一个位置。默认值为：0
        private int front;
        // 队列尾，指向队列尾的位置。默认值为：0
        private int rear;
        // 数据
        private E[] data;
        // 容量
        private int maxSize;

        private int size;

        /**
         * 构造函数
         * @param capacity
         */
        public MyQueue(int capacity) {
            this.front = 0;
            this.rear = 0;
            this.size = 0;
            // 预留一个判断是否队满。
            this.maxSize = capacity;
            this.data = (E[])new Object[maxSize];
        }
        /**
         * 判断是否已经满
         * @return
         */
        public boolean isFull(){
            return size == maxSize;
        }

        /**
         * 是否为空
         * @return
         */
        public boolean isEmpty(){
            return size == 0;
        }

        /**
         * 获取大小
         * @return
         */
        public int size(){
            return size;
        }

        /**
         * 添加元素
         * @param element
         * @return
         */
        public boolean add(E element){
            if(element == null) {
                throw new NullPointerException("can not add element be null");
            }
            data[rear] = element;
            rear = (rear + 1) % maxSize;
            if (size < maxSize)
            size++;
            return true;
        }

        /**
         * 返回队列中的第一个元素
         * @return
         */
        public E get(){
            if(isEmpty()){
                throw new NoSuchElementException();
            }
            rear = (rear - 1 + maxSize) % maxSize;
            E element = data[rear];
            size--;
            return element;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();
            if(isEmpty()){
                return buffer.append("[]").toString();
            }

            buffer.append("[");
            for (int i = front; i < front + size(); i++) {
                buffer.append(data[i%maxSize].toString()).append(",");
            }
            // 删除最后一个逗号
            buffer.deleteCharAt(buffer.length()-1);
            buffer.append("]");

            return buffer.toString();
        }
    }

