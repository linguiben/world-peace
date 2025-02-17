
现阶段的LLM类似一部字典，可以回答问题，但是不能理解问题，不能推理，不能解决问题。DeepSeek-R1是一个新的LLM，可以推理，可以解决问题。
DeepSeek-R1是一个RLLLM，是一个强化学习的LLM。DeepSeek-R1是一个新的LLM，是一个新的时代。

```java
微软CEO萨蒂亚·纳德拉（Satya Nadella）周三在瑞士达沃斯举行的世界经济论坛上表示：“看到DeepSeek的新模型，无论是他们如何真正有效地完成了一个开
源模型来进行推理时间计算，还是计算效率方面，都令人印象深刻。我们应该非常认真地对待这一发展。”
DeepSeek-R1 模型下载：
DeepSeek-R1-Zero：https://huggingface.co/deepseek-ai/DeepSeek-R1-Zero
DeepSeek-R1：https://huggingface.co/deepseek-ai/DeepSeek-R1
DeepSeek-R1-Zero 和 DeepSeek-R1 基于DeepSeek-V3-Base 进行训练。

DeepSeek-R1-Distill 模型下载：
DeepSeek-R1-Distill-Qwen-1.5B：https://huggingface.co/deepseek-ai/DeepSeek-R1-Distill-Qwen-1.5B
DeepSeek-R1-Distill-Qwen-7B：https://huggingface.co/deepseek-ai/DeepSeek-R1-Distill-Qwen-7B
DeepSeek-R1-Distill-Qwen-14B：https://huggingface.co/deepseek-ai/DeepSeek-R1-Distill-Qwen-14B
DeepSeek-R1-Distill-Qwen-32B：https://huggingface.co/deepseek-ai/DeepSeek-R1-Distill-Qwen-32B

download from modelscope
https://modelscope.cn/models/deepseek-ai/DeepSeek-R1/summary
```

### 关于java类加载器的问题：
```java
1. 若java启动时，在classpath中存在两个不同版本的jar包，且两个jar包中存在同名但不同版本的class，那么在运行时，会加载哪个类呢？
   // 此题目旨在考查面试者对于java运行时classpath的理解。
   [答案]: 在运行时，会加载classpath中排在前面的jar包中的类。
2. 若在jar包中自定义了java.lang.String类，那么在运行时，会加载哪个类呢？
   // 此题目旨在考查面试者对于类加载器的理解，以及类加载器的双亲委派模型的理解。
   [答案]: 在运行时，默认会加载jdk中的java.lang.String类。
3. 若想加载自定义的java.lang.String类，应该怎么做？
   [答案]: 通过自定义类加载器，重写loadClass方法，实现自定义类加载器加载自定义的java.lang.String类。
4. 当加载了自定义的java.lang.String类后，会覆盖jdk中定义的java.lang.String类吗？此时，jdk中定义的java.lang.String类还能使用吗？
   // 此题目旨在考查面试者对于类加载器的理解，以及类加载器的双亲委派模型的理解。
   [答案]: 不会覆盖。通过Class<?> classA = ClassLoaderA.loadClass("java.lang.String")获取字节码使用。
// 以上问题层层深入，考查面试者对于类加载器的理解，以及类加载器的双亲委派模型的理解。

5. 若一个类已经被加载到内存中，可以再次加载吗？ *** 加试,可忽略 ***
        [答案]: 若一个类已经被加载到内存中，再次加载时，会抛出异常：
java.lang.LinkageError: loader (instance of  sun/misc/Launcher$AppClassLoader): attempted  duplicate class
definition for name: "com/xxx/xxx/xxx"
```
### 编写java类时，什么时候用无参构造器，什么时候用有参构造器？
```java
6. 编写java类时，什么时候用无参构造器，什么时候用有参构造器？
   // 此题目旨在考查面试者对于java类构造器的理解。
   [答案]: 可以简单理解成有参构造器用于初始化对象，把类的必要属性(考虑是否需要设计成final)放到有参构造器中。这样的作用是方便使用者在创建对象时，就能够初始化对象的必要属性，避免了对象的不完整性。
```

Stirng -> StringBuffer -> StringBuilder -> ThreadSafe -> ThreadLocal -> 
-> Stream

GPT： Generative Pre-trained Transformer
![img_1.png](history_of_transformer.png)
![img_2.png](accumulation_of_dimn.png)

Q: Which of the following AI models are trained without supervised fine-tuning (SFT)?
A. DeepSeek-R1
B. DeepSeek-R1-Zero
C. AlphaGo
D. AlphaGo Zero
E. ChatGPT
F. LLaMa
G. Qwen

可以从这里购买GPU算力：https://www.autodl.com

【DeepSeek论文精读】7. 总结：DeepSeek 的发展历程与关键技术
https://blog.csdn.net/youcans/article/details/143723178

Brief analysis of DeepSeek R1 and its implications for Generative AI
  -- The author is from the Turing Institute
https://arxiv.org/html/2502.02523v3

Deepseek papers collection by Hugging Face
https://huggingface.co/collections/Presidentlin/deepseek-papers-674c536aa6acddd9bc98c2ac

DeepSeek-V3 Technical Report
https://arxiv.org/html/2412.19437v1


DeepSeek LLM: Scaling Open-Source Language Models with Longtermism
https://arxiv.org/abs/2401.02954
https://github.com/deepseek-ai/DeepSeek-LLM

DeepSeek-R1 Paper Explained – A New RL LLMs Era in AI?
https://aipapersacademy.com/deepseek-r1/

Levels of AGI for Operationalizing Progress on the Path to AGI
https://arxiv.org/pdf/2311.02462

How Far Are We From AGI: Are LLMs All We Need?
https://arxiv.org/abs/2405.10313

DeepSeek-Prover-V1.5: Harnessing Proof Assistant Feedback for Reinforcement Learning and Monte-Carlo Tree Search
https://arxiv.org/abs/2408.08152
https://github.com/deepseek-ai/DeepSeek-Prover-V1.5

DeepSeek-V2: A Strong, Economical, and Efficient Mixture-of-Experts Language Model
https://arxiv.org/abs/2405.04434

DeepSeek-V3 Technical Report
https://arxiv.org/abs/2412.19437

DeepSeek-R1: Incentivizing Reasoning Capability in LLMs via Reinforcement Learning
https://arxiv.org/abs/2501.12948

https://github.com/deepseek-ai
![img.png](repo_of_ds.png)