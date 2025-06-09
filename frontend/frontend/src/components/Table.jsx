import '../styles/Table.css'; // Regular CSS import

function Table({ headers = [], data = [], renderRow }) {
  if (!Array.isArray(headers) || !Array.isArray(data)) {
    return <p>No data available.</p>;
  }

  return (
    <table className="table">
      <thead>
        <tr>
          {headers.map((header, index) => (
            <th key={index} className="th">{header}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.length > 0 ? (
          data.map((item, index) => renderRow(item, index))
        ) : (
          <tr>
            <td colSpan={headers.length} className="td">No records found.</td>
          </tr>
        )}
      </tbody>
    </table>
  );
}

export default Table;
