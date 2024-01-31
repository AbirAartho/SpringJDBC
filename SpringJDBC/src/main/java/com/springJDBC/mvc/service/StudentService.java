package com.springJDBC.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.springJDBC.mvc.CustomRowMapper;
import com.springJDBC.mvc.model.Student;

//@Service

@org.springframework.stereotype.Service
public class StudentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Student student) {
		if (student.getId() == null) {
			return jdbcTemplate.update(" insert into student " + " (name, roll_no, email_id) values(?,?,?)",
					student.getName(), student.getRollNo(), student.getEmailId());
		} else {
			return jdbcTemplate.update(" update student set name = ?, " + "roll_no = ?, email_id = ? where id = ?",
					student.getName(), student.getRollNo(), student.getEmailId(), student.getId());

		}

	}

	public int update(Student student) {
		return jdbcTemplate.update(" update student set name = ?, " + "roll_no = ?, email_id = ? where id = ?",
				student.getName(), student.getRollNo(), student.getEmailId(), student.getId());
	}

	public int deleteById(Long id) {
		return jdbcTemplate.update("delete from student where id = ?", id);
	}

	public List<Student> findAll() {
		String query = "select * from student ";
		return jdbcTemplate.query(query, (rs, rowNum) -> {
			System.out.println(rowNum);
			return new Student(rs.getLong("id"), rs.getString("name"), rs.getString("roll_no"),
					rs.getString("email_id"));
		}

		);
	}

	public List<Student> findAll2() {
		String sql = "select * from student ";
		return jdbcTemplate.query(sql, new CustomRowMapper());
	}

	public Optional<Student> findById2(Long id) {
		return jdbcTemplate.queryForObject("select * from student where id = ?", new Object[] { id },
				(rs, rowNum) -> Optional.of(new Student(rs.getLong("id"), rs.getString("name"), rs.getString("roll_no"),
						rs.getString("email_id"))));
	}

	public Student getDataByID(Long id) {
		String sql = "select * from student where id = ? ";
		return jdbcTemplate.query(sql, new CustomRowMapper(), id).stream().findFirst().get();
	}

//	    @Override
//	    public List<Book> findByNameAndPrice(String name, BigDecimal price) {
//	        return jdbcTemplate.query(
//	                "select * from books where name like ? and price <= ?",
//	                new Object[]{"%" + name + "%", price},
//	                (rs, rowNum) ->
//	                        new Book(
//	                                rs.getLong("id"),
//	                                rs.getString("name"),
//	                                rs.getBigDecimal("price")
//	                        )
//	        );
//	    }
//
//	    @Override
//	    public String findNameById(Long id) {
//	        return jdbcTemplate.queryForObject(
//	                "select name from books where id = ?",
//	                new Object[]{id},
//	                String.class
//	        );
//	    }
//
//	    @Override
//	    public int[] batchUpdate(List<Book> books) {
//
//	        return this.jdbcTemplate.batchUpdate(
//	                "update books set price = ? where id = ?",
//	                new BatchPreparedStatementSetter() {
//
//	                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//	                        ps.setBigDecimal(1, books.get(i).getPrice());
//	                        ps.setLong(2, books.get(i).getId());
//	                    }
//
//	                    public int getBatchSize() {
//	                        return books.size();
//	                    }
//
//	                });
//
//	    }
//
//	    @Override
//	    public int[][] batchUpdate(List<Book> books, int batchSize) {
//
//	        int[][] updateCounts = jdbcTemplate.batchUpdate(
//	                "update books set price = ? where id = ?",
//	                books,
//	                batchSize,
//	                new ParameterizedPreparedStatementSetter<Book>() {
//	                    public void setValues(PreparedStatement ps, Book argument) throws SQLException {
//	                        ps.setBigDecimal(1, argument.getPrice());
//	                        ps.setLong(2, argument.getId());
//	                    }
//	                });
//	        return updateCounts;
//
//	    }
//
//	    @Override
//	    public int[] batchInsert(List<Book> books) {
//
//	        return this.jdbcTemplate.batchUpdate(
//	                "insert into books (name, price) values(?,?)",
//	                new BatchPreparedStatementSetter() {
//
//	                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//	                        ps.setString(1, books.get(i).getName());
//	                        ps.setBigDecimal(2, books.get(i).getPrice());
//	                    }
//
//	                    public int getBatchSize() {
//	                        return books.size();
//	                    }
//
//	                });
//	    }
//
//	    // Any failure causes the entire operation to roll back, none of the book will be added
//	    @Transactional
//	    @Override
//	    public int[][] batchInsert(List<Book> books, int batchSize) {
//
//	        int[][] updateCounts = jdbcTemplate.batchUpdate(
//	                "insert into books (name, price) values(?,?)",
//	                books,
//	                batchSize,
//	                new ParameterizedPreparedStatementSetter<Book>() {
//	                    public void setValues(PreparedStatement ps, Book argument) throws SQLException {
//	                        ps.setString(1, argument.getName());
//	                        ps.setBigDecimal(2, argument.getPrice());
//	                    }
//	                });
//	        return updateCounts;
//
//	    }
//
//	    // https://www.postgresql.org/docs/7.3/jdbc-binary-data.html
//	    // https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-lob
//	    @Override
//	    public void saveImage(Long bookId, File image) {
//
//	        try (InputStream imageInStream = new FileInputStream(image)) {
//
//	            jdbcTemplate.execute(
//	                    "INSERT INTO book_image (book_id, filename, blob_image) VALUES (?, ?, ?)",
//	                    new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
//	                        protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
//	                            ps.setLong(1, 1L);
//	                            ps.setString(2, image.getName());
//	                            lobCreator.setBlobAsBinaryStream(ps, 3, imageInStream, (int) image.length());
//	                        }
//	                    }
//	            );
//
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	    }
//
//	    @Override
//	    public List<Map<String, InputStream>> findImageByBookId(Long bookId) {
//
//	        List<Map<String, InputStream>> result = jdbcTemplate.query(
//	                "select id, book_id, filename, blob_image from book_image where book_id = ?",
//	                new Object[]{bookId},
//	                new RowMapper<Map<String, InputStream>>() {
//	                    public Map<String, InputStream> mapRow(ResultSet rs, int i) throws SQLException {
//
//	                        String fileName = rs.getString("filename");
//	                        InputStream blob_image_stream = lobHandler.getBlobAsBinaryStream(rs, "blob_image");
//
//	                        // byte array
//	                        //Map<String, Object> results = new HashMap<>();
//	                        //byte[] blobBytes = lobHandler.getBlobAsBytes(rs, "blob_image");
//	                        //results.put("BLOB", blobBytes);
//
//	                        Map<String, InputStream> results = new HashMap<>();
//	                        results.put(fileName, blob_image_stream);
//
//	                        return results;
//
//	                    }
//	                });
//
//	        return result;
//	    }

}
