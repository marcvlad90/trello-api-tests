package dao.board;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.tools.models.Board;

@ImplementedBy(BoardDao.class)
public interface BoardAbstractDao {
    public Board getBoardByName(String name);

    public void saveBoard(Board board);

    public void removeBoard(Board board);

    public List<Board> getAllBoards();
}
