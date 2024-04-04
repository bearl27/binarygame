# プロダクト名 Binaryの壁
<!-- プロダクト名に変更してください -->


<!-- プロダクト名・イメージ画像を差し変えてください -->


## 開発者
bearl（個人開発）
<!-- チームIDとチーム名を入力してください -->


## 背景・課題・解決されること
最近，スイカゲームや漢字でGOなど単純で簡単なゲームが流行っている.そこで，私もその流行に乗っかり，簡単なゲームを開発しようと考えた．

そこで，開発目的を</br>
**自分や身の回りの人に役立つアプリケーション**として

私は情報理工学部に所属しているため，この学部生に役立つような2進数に関係するゲームを思いついた．

## プロダクト説明
Binaryの壁という10進数の数字を2進数に変えるという単純なゲーム．
0,1,削除,確定の4つのボタンだけで構成されており，シンプルなUI．
Binaryの壁では楽しく2進数に慣れ親しむことを目的としている．

プレイヤーに楽しさややりごたえを提供するために，3つの難易度とラウンド性，ランキング機能を追加した．  
<!-- 開発したプロダクトの説明を入力してください -->


## 操作説明・デモ動画
[デモ動画はこちら](https://www.youtube.com/watch?v=_FAA15ARmas)
<!-- 開発したプロダクトの操作説明について入力してください。また、操作説明デモ動画があれば、埋め込みやリンクを記載してください -->
**ゲームの説明**  
ホーム画面，ゲーム画面，ランキング画面，ゲーム説明画面の4画面で構成されているため，各々の説明をしていく．

<h3>ホーム画面</h3>
ホーム画面から他の3画面に移動することができる.

<h3>ゲーム画面</h3>
ゲームの難易度は3つで，ホーム画面でゲームの難易度を選べぶことができ，各難易度に3つのRoundがあり，Roundがあがるごとに難しくなる．問題を解くごとに得点をもらえ，難易度が高いほどに得点も高い． ハートの数で間違えられる数の制限している．ゲームオーバーとゲームクリアを告げる表示が出てきて，そこに，リトライボタンとホーム画面にもどるボタンの作成した．

<h3>ランキング画面</h3>

<h3>ゲーム説明画面</h3>
 ゲームの説明が書かれている．
 
## 注力したポイント
シンプルで分かりやすいデザイン．

## 改善点
<ul>
  <li>
    各問題に対して制限時間を設定する. →時間をかければ高得点を誰でもとれるため.
  </li>
  <li>
    同じ問題を連続で出さないようにする →ゲームのおもしろさをあげるため.
  </li>
  <li>
    ゲームクリアの証明.例)ホーム画面に難易度の色の星を作る.
  </li>
  <li>
    タイムアタックなどほかのゲームモードの実装. 
  </li>
  <li>
    ・全ての難易度をクリアしたときに，新たな難易度，ゲームモードができるようにする. </br>
    例)10進数を16進数，2進数を16進数にする. →ゲームのやり込み要素を増やし，ユーザーを飽きさせないため. 
  </li>
  <li>
    正解，不正解時の効果音やゲームのタイトルコールなどの作成.
  </li>
  <li>
    問題を壁として正解したら壁を乗り越える，壊すなどのアニメーションの作成.
→五感の内の聴覚を増やし，ゲームの面白さを増やす.
  </li>
</ul> 

## 使用技術
**開発環境:** Android Studio
- フロントエンド: XML
- バックエンド: Java(17.07)
            

<!--
markdownの記法はこちらを参照してください！
https://docs.github.com/ja/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax
-->
