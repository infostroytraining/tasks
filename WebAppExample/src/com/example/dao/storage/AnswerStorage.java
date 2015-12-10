package com.example.dao.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.entity.Answer;

public class AnswerStorage {

	private Map<Integer, Answer> storage;
	private AtomicInteger id = new AtomicInteger();

	public AnswerStorage() {
		storage = new HashMap<>();
	}

	public Answer add(Answer answer) {
		int id = generateId();
		answer.setId(id);
		storage.put(id, answer);
		return answer;
	}

	public List<Answer> all() {
		return new ArrayList<Answer>(storage.values());
	}

	private int generateId() {
		return id.incrementAndGet();
	}
}
