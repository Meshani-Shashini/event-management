import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';  // <-- import useNavigate
import axios from 'axios';
import Table from '../components/Table';
import Button from '../components/Button';
import Input from '../components/Input';
import '../styles/EventDetailPage.css';

function EventDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();  // <-- initialize navigate
  const [event, setEvent] = useState(null);
  const [attendees, setAttendees] = useState([]);
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    setError(null);

    axios
      .get(`/api/events/${id}`) // fetch event details
      .then((response) => setEvent(response.data))
      .catch(() => setError('Failed to load event details.'));

    axios
      .get(`/api/attendees/event/${id}`) // fetch attendees for event
      .then((response) => setAttendees(response.data))
      .catch(() => setError('Failed to load attendees.'))
      .finally(() => setLoading(false));
  }, [id]);

  const handleRegister = () => {
    if (!name || !email) {
      setError('Name and email are required.');
      return;
    }

    setError(null);

    axios
      .post('/api/attendees', { name, email, eventId: Number(id) })
      .then((response) => {
        // Add new attendee to state list
        setAttendees([...attendees, response.data]);
        setName('');
        setEmail('');
      })
      .catch((error) => {
        setError(error.response?.data?.message || 'Failed to register attendee.');
      });
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div style={{ color: 'red' }}>Error: {error}</div>;
  if (!event) return <div>Event not found.</div>;

  const headers = ['Name', 'Email'];
  const renderRow = (attendee) => (
    <tr key={attendee.id}>
      <td>{attendee.name}</td>
      <td>{attendee.email}</td>
    </tr>
  );

  return (
    <div className="container">
      <h1>{event.name}</h1>
      <p>{event.description}</p>
      <p>Date: {event.date}</p>
      <p>Location: {event.location}</p>
      <p>Capacity: {event.capacity - event.remainingCapacity} / {event.capacity}</p>

      {/* Update button to navigate to edit page */}
      <Button onClick={() => navigate(`/events/${id}/update`)}>Update Event</Button>

      <h2>Register</h2>
      <Input
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Name"
        required
      />
      <Input
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="Email"
        required
      />
      <Button onClick={handleRegister}>Register</Button>

      <h2>Attendees</h2>
      <Table headers={headers} data={attendees} renderRow={renderRow} />
    </div>
  );
}

export default EventDetailPage;
