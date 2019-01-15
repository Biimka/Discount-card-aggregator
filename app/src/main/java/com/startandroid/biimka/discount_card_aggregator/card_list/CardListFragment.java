package com.startandroid.biimka.discount_card_aggregator.card_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.startandroid.biimka.discount_card_aggregator.BaseFragment;
import com.startandroid.biimka.discount_card_aggregator.CardListItem;
import com.startandroid.biimka.discount_card_aggregator.CardRepo;
import com.startandroid.biimka.discount_card_aggregator.CardRepoImpl;
import com.startandroid.biimka.discount_card_aggregator.R;

import java.util.Collections;
import java.util.List;

public class CardListFragment extends BaseFragment implements CardListView {
    @InjectPresenter
    CardListPresenter mCardListPresenter;

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private CardRepo cardRepo = CardRepoImpl.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cardlist, null);

        final Toolbar toolbar = rootView.findViewById(R.id.toolbarCardList);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        adapter = new MyAdapter();
        adapter.setListener(new MyAdapter.Listener() {
            @Override
            public void onItemClicked(CardListItem card) {
                final Bundle bundle = new Bundle();
                bundle.putLong("id", card.getId());
                mCardListPresenter.toPutBundle(R.id.fragmentBarcode, bundle);
            }

            @Override
            public void onItemClickedLong(CardListItem card) {
                mCardListPresenter.onCardItemLongClick(card);
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        final FloatingActionButton addDiscountCardFAB = rootView.findViewById(R.id.addDiscountCardFAB);

        addDiscountCardFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Bundle bundle = new Bundle();
                mCardListPresenter.toPutBundle(R.id.fragmentCard, bundle);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        long id = info.id;
        switch (item.getItemId()) {
            case R.id.menuEdit:
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                mCardListPresenter.toPutBundle(R.id.fragmentCard, bundle);
                break;
            case R.id.menuDelete:
                cardRepo.deleteCard(id);
                adapter.setCards(cardRepo.getCards());
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCardItemLongClicked(CardListItem card) {
        registerForContextMenu(recyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setCards(cardRepo.getCards());
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<CardListItem> cards = Collections.emptyList();
        private Listener listener;

        public void setListener(Listener listener) {
            this.listener = listener;
        }

        public interface Listener {
            void onItemClicked(CardListItem card);

            void onItemClickedLong(CardListItem card);
        }

        public void setCards(List<CardListItem> cards) {
            this.cards = cards;
            notifyDataSetChanged();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewCardName;
            private ImageView imageFront;

            private MyViewHolder(View v) {
                super(v);
                textViewCardName = v.findViewById(R.id.textViewCardName);
                imageFront = v.findViewById(R.id.frontImage);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClicked(cards.get(getAdapterPosition()));
                    }
                });
                v.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        listener.onItemClickedLong(cards.get(getAdapterPosition()));
                        return false;
                    }
                });
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
            myViewHolder.textViewCardName.setText(cards.get(position).getName());
            myViewHolder.imageFront.setImageBitmap(cards.get(position).getImageFront());

        }

        @Override
        public int getItemCount() {
            return cards.size();
        }
    }
}
