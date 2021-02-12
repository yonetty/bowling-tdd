import spock.lang.Specification

class GameSpec extends Specification {
  private Game g;

  def setup() {
    g = new Game();
  }

  def "2回投球したときのスコアが正しい"() {
    when:
      g.add(5)
      g.add(4)
    then:
      g.score() == 9
  }

  def "4回投球したときのスコアが正しい"() {
    when:
      g.add(5)
      g.add(4)
      g.add(7)
      g.add(2)
    then:
      g.score() == 18
      g.scoreForFrame(1) == 9
      g.scoreForFrame(2) == 18
  }

  def "スペアを出したときのスコアが正しい"() {
    when:
      g.add(3)
      g.add(7)
      g.add(3)
    then:
      g.scoreForFrame(1) == 13
  }

  def "スペアを出したときの次のフレームのスコアが正しい"() {
    when:
      g.add(3)
      g.add(7)
      g.add(3)
      g.add(2)
    then:
      g.scoreForFrame(1) == 13
      g.scoreForFrame(2) == 18
      g.score() == 18
  }

  def "ストライクを出したときのスコアが正しい"() {
    when:
      g.add(10)
      g.add(3)
      g.add(6)
    then:
      g.scoreForFrame(1) == 19
      g.score() == 28
  }

  def "パーフェクトゲーム"() {
    when:
      12.times {g.add(10)}
    then:
      g.score() == 300
  }

  def "サンプルゲーム"() {
    when:
      [1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6]
          .forEach({g.add(it)})
    then:
      g.score() == 133
  }

  def "パーフェクトゲーム未遂"() {
    when:
      11.times {g.add(10)}
      g.add(9)
    then:
      g.score() == 299
  }

  def "パーフェクトゲームペースも最後にスペア"() {
    when:
      9.times {g.add(10)}
      g.add(9)
      g.add(1)
      g.add(1)
    then:
      g.score() == 270
  }

}
