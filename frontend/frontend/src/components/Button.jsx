import '../styles/Button.css'; // Make sure the path is correct

function Button({ children, onClick, type = 'button' }) {
  return (
    <button className="button" onClick={onClick} type={type}>
      {children}
    </button>
  );
}

export default Button;
