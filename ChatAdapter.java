public class ChatAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final int CONNECTION_ROW = 0;
    private static final int CONNECTION_HEADER = 1;

    private List<String> profileIds;
    private DatabaseManager database;

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImage;
        TextView name;
        TextView lastMessageSent;

        public ProfileViewHolder(View view) {
            super(view);

            avatarImage = (TextView) view.findViewById(R.id.avatar_image);
            name = (TextView) view.findViewById(R.id.name);
            lastMessageSent = (TextView) view.findViewById(R.id.last_message_sent);
        }
    }

    public static class ChatHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView header;

        public ProfileViewHolder(View view) {
            super(view);
            header = (TextView) view.findViewById(R.id.header);
        }
    }

    public ChatAdapter(List<String> profileIds, DatabaseManager database) {
        this.profileIds = profileIds;
        this.database = database;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        switch(viewType) {
            case CONNECTION_ROW: 
                View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_row_view, viewGroup, false);

                return new ChatViewHolder(view);

            case CONNECTION_HEADER: 
                View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.chat_header_view, viewGroup, false);

                return new ChatHeaderViewHolder(view);
        }
        
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Profile profile = databaes.getProfile(profileIds.get(position));

        viewHolder.avatarImage.loadImage(profile.getPhotoUrl());
        viewHolder.name.setText(profile.getName);
        viewHolder.lastMessageSent.setText(profile.getLastMessageSent());
    }

    @Override
    public int getItemCount() {
        return profileIds.size();
    }
}