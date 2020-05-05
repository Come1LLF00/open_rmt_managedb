package parsing.instructions.concrete.extended;

import communication.Report;
import entities.Mappable;
import entities.Organization;
import parsing.customer.Receiver;
import parsing.instructions.concrete.ConDecree;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Команда, находящая информацию об
 * элементе, имеющего поле с максимальным значением
 * @param <K> ключ коллекции
 * @param <R> поле, по которому сравниваем
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class MaxBy<K, V extends Mappable<K>, R extends Comparable<? super R>> extends ConDecree {
  protected final Function<? super V,? extends R> keySearcher;
  /**
   * Конструктор, устанавливающий ссылку на
   * управленца коллекцией, а также
   * устанавливает геттер, которым мы берем поле
   * @param sieve текущий управленец коллекцией
   */
  public MaxBy(Receiver<K, V> sieve, Function<V, R> field) {
    super(sieve);
    keySearcher = field;
  }

  /**
   * Метод по нахождению максимального
   * элемента по определенному полю
   * @return информацию об элементе с максимальным значением поля
   */
  @Override
  public Report execute() {
    // взять HashMap из коллекции, где ключи - ключи организации, а значения - поле его
    Map<K, R> buffer = sieve.getBy(keySearcher);
    // найти в отображении максимальный элемент, вернуть его ключ
    Optional<Map.Entry<K, R>> maxim =
        buffer.entrySet()
        .stream()
        .max((Map.Entry<K, R> enter1, Map.Entry<K, R> enter2)->(enter1.getValue().compareTo(enter2.getValue())));
    K maxim_key = maxim.get().getKey();
    // найти в исходной коллекции тот элемент
    Mappable<K> recology = null;
    sieve.search(maxim_key, recology, (element)->true);
    // вернуть информацию об элементе
    return new Report(0, recology.toString());
  }
}