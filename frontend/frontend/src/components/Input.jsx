import "../styles/Input.css";

function Input({ type, name, value, onChange, placeholder, required }) {
  return (
    <input
      type={type}
      name={name}           // <--- Add this line
      value={value}
      onChange={onChange}
      placeholder={placeholder}
      required={required}
      className="input"
    />
  );
}

export default Input;
